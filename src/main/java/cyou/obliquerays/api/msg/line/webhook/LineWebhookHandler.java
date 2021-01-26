/**
 *  Copyright 2020 tasekida
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package cyou.obliquerays.api.msg.line.webhook;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import cyou.obliquerays.api.msg.line.model.LineWebhook;
import cyou.obliquerays.api.msg.line.model.LineWebhookEvent;
import cyou.obliquerays.api.msg.slack.api.SlackIncomingWebhook;
import cyou.obliquerays.api.msg.slack.model.SlackIncomingWebhookPayload;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

/**
 * Lineからこのアプリケーションへメッセージを受信する処理
 * @see <a href="https://developers.line.biz/ja/reference/messaging-api/#webhooks">Line Messaging API Webhook</a>
 */
public class LineWebhookHandler implements HttpHandler {
    //// Fields
    /**
     * ロガー
     */
    private static final Logger logger = Logger.getLogger(LineWebhookHandler.class.getName());
    /**
     *  Slackへメッセージを送信するクライアント
     */
    private final SlackIncomingWebhook slackApi;
    /**
     *  メッセージ認証鍵
     */
    private SecretKeySpec key = new SecretKeySpec(System.getenv("LINE_WEBHOOK_CHANNEL_SECRET").getBytes(), "HmacSHA256");
    /**
     *  メッセージ認証コード
     */
    private Mac mac;

    //// Constructors
    /**
     * デフォルトコンストラクター<br>
     * このアプリケーションからSlackへメッセージを送信する処理を初期化<br>
     * メッセージ認証コードオブジェクトを生成
     * @throws NoSuchAlgorithmException HmacSHA256は未サポート
     */
    public LineWebhookHandler() throws NoSuchAlgorithmException {
        this.slackApi = new SlackIncomingWebhook();
        this.mac = Mac.getInstance("HmacSHA256");
    }

    /**
     * Lineからこのアプリケーションへメッセージを受信した時の処理
     */
    @Override
    public void handle(HttpExchange _exchange) {

        for (Entry<String, List<String>> header : _exchange.getRequestHeaders().entrySet()) {
            for (Object value : header.getValue()) {
                logger.log(Level.CONFIG, new StringBuilder().append(header.getKey()).append(" = ").append(value).toString());
            }
        }

        String body = "{}";
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(_exchange.getRequestBody(), StandardCharsets.UTF_8));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(_exchange.getResponseBody(), StandardCharsets.UTF_8))) {
            body = reader.lines().collect(Collectors.joining());
            String response = "{}";
            logger.log(Level.CONFIG, new StringBuilder().append("BODY").append(" = ").append(body).toString());
            _exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, response.length());
            writer.write(response);
            writer.flush();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Lineからメッセージの受信に失敗", e);
            return;
        }

        try {
            this.mac.init(this.key);
            byte[] source = body.getBytes(StandardCharsets.UTF_8);
            String signature = Base64.getEncoder().encodeToString(this.mac.doFinal(source));
            String x_line_signature = _exchange.getRequestHeaders().getFirst("X-line-signature");
            if (null != x_line_signature && null != signature && signature.equals(x_line_signature)) {
                logger.log(Level.CONFIG, "署名の認証に成功");
            } else {
                logger.log(Level.WARNING, "署名の検証に失敗");
            }
        } catch (InvalidKeyException e) {
            logger.log(Level.WARNING, "署名の検証に失敗", e);
            return;
        }

        try (Jsonb jsonb = JsonbBuilder.create()) {
            LineWebhook line = jsonb.fromJson(body, LineWebhook.class);
            SlackIncomingWebhookPayload slack = this.lineToSlack(line);
            if (null != slack) {
                this.slackApi.send(slack);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Slackへのメッセージの送信に失敗", e);
            return;
        }
    }

    /**
     * Lineから受信したメッセージをSlackへ送信するメッセージに変換
     * @param _line Lineから受信したメッセージ
     * @return Slackへ送信するメッセージ
     */
    private SlackIncomingWebhookPayload lineToSlack(LineWebhook _line) {
        SlackIncomingWebhookPayload slack = null;
        if (null != _line) {
            logger.log(Level.CONFIG, "destination = " + _line.getDestination());
            for (LineWebhookEvent event : _line.getEvents()) {
                switch(event.getType()) {
                case "message":
                    logger.log(Level.CONFIG, "event.type = " + event.getType());
                    logger.log(Level.CONFIG, "event.mode = " + event.getMode());
                    logger.log(Level.CONFIG, "event.timestamp = " + event.getTimestamp());
                    logger.log(Level.CONFIG, "event.source.type = " + event.getSource().getType());
                    logger.log(Level.CONFIG, "event.source.userId = " + event.getSource().getUserId());
                    logger.log(Level.CONFIG, "event.source.groupId = " + event.getSource().getGroupId());
                    logger.log(Level.CONFIG, "event.source.roomId = " + event.getSource().getRoomId());
                    logger.log(Level.CONFIG, "event.message.id = " + event.getMessage().getId());
                    logger.log(Level.CONFIG, "event.message.type = " + event.getMessage().getType());
                    logger.log(Level.CONFIG, "event.message.text = " + event.getMessage().getText());
                    if (null != event.getSource().getGroupId() && null != event.getSource().getUserId()
                            && event.getSource().getGroupId().equals(System.getenv("LINE_TARGET_GROUP_ID"))
                            && event.getSource().getUserId().equals(System.getenv("LINE_TARGET_USER_ID"))
                            && event.getMessage().getType().equals("text")) {
                        slack = new SlackIncomingWebhookPayload();
                        slack.setText(event.getMessage().getText());
                        slack.setMrkdwn(true);
                    }
                    break;
                default:
                    logger.log(Level.CONFIG, "event.type = " + event.getType());
                    logger.log(Level.CONFIG, "event.mode = " + event.getMode());
                    logger.log(Level.CONFIG, "event.timestamp = " + event.getTimestamp());
                }
            }
        }
        return slack;
    }
}
