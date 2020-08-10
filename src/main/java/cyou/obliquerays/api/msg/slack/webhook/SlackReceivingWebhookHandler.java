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
package cyou.obliquerays.api.msg.slack.webhook;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.json.bind.Jsonb;

import org.apache.johnzon.jsonb.JohnzonBuilder;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import cyou.obliquerays.api.msg.line.api.LineMessagingAPIHandler;
import cyou.obliquerays.api.msg.line.model.LineMessagingApi;
import cyou.obliquerays.api.msg.line.model.LineMessagingApiMessage;
import cyou.obliquerays.api.msg.slack.model.SlackReceivingWebhook;

/**
 * Slackからこのアプリケーションへメッセージを受信する処理
 * @see <a href="https://api.slack.com/events-api">Slack Events API</a>
 */
public class SlackReceivingWebhookHandler implements HttpHandler {
    //// Fields
    /**
     * ロガー
     */
    private static final Logger logger = Logger.getLogger(SlackReceivingWebhookHandler.class.getName());
    /**
     *  LineMessagingAPI送信クライアント
     */
    private final LineMessagingAPIHandler lineApi;

    //// Constructors
    /**
     * デフォルトコンストラクター<br>
     * このアプリケーションからLineへメッセージを送信する処理を初期化
     * @throws NoSuchAlgorithmException Lineへメッセージを送信する処理の初期化に失敗
     * @throws KeyManagementException Lineへメッセージを送信する処理の初期化に失敗
     */
    public SlackReceivingWebhookHandler() throws KeyManagementException, NoSuchAlgorithmException {
        this.lineApi = new LineMessagingAPIHandler();
    }

    /**
     * Slackからこのアプリケーションへメッセージを受信した時の処理
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
            logger.log(Level.CONFIG, new StringBuilder().append("BODY").append(" = ").append(body).toString());
            _exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, body.length());
            writer.write(body);
            writer.flush();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Slackからメッセージの受信に失敗", e);
        }

        try (Jsonb jsonb = new JohnzonBuilder().build()) {
            SlackReceivingWebhook slack = jsonb.fromJson(body, SlackReceivingWebhook.class);
            LineMessagingApi line = this.slackToLine(slack);
            if (null != line) {
                this.lineApi.send(line);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Lineへのメッセージの送信に失敗", e);
        }
    }

    /**
     * Slackから受信したメッセージをLineへ送信するメッセージに変換
     * @param _slack Slackから受信したメッセージ
     * @return Lineへ送信するメッセージ
     */
    private LineMessagingApi slackToLine(SlackReceivingWebhook _slack) {
        LineMessagingApi line = null;
        if (null != _slack) {
            logger.log(Level.CONFIG, "token = " + _slack.getToken());
            logger.log(Level.CONFIG, "team_id = " + _slack.getTeam_id());
            logger.log(Level.CONFIG, "api_app_id = " + _slack.getApi_app_id());
            logger.log(Level.CONFIG, "type = " + _slack.getType());
            logger.log(Level.CONFIG, "authed_users = " + _slack.getAuthed_users());
            logger.log(Level.CONFIG, "event_id = " + _slack.getEvent_id());
            logger.log(Level.CONFIG, "event_time = " + _slack.getEvent_time());
            if (null != _slack.getEvent()) {
                logger.log(Level.CONFIG, "event.type = " + _slack.getEvent().getType());
                logger.log(Level.CONFIG, "event.event_ts = " + _slack.getEvent().getEvent_ts());
                logger.log(Level.CONFIG, "event.user = " + _slack.getEvent().getUser());
                logger.log(Level.CONFIG, "event.ts = " + _slack.getEvent().getTs());
                logger.log(Level.CONFIG, "event.item = " + _slack.getEvent().getItem());
                logger.log(Level.CONFIG, "event.client_msg_id = " + _slack.getEvent().getClient_msg_id());
                logger.log(Level.CONFIG, "event.text = " + _slack.getEvent().getText());
                logger.log(Level.CONFIG, "event.team = " + _slack.getEvent().getTeam());
                logger.log(Level.CONFIG, "event.channel = " + _slack.getEvent().getChannel());
                logger.log(Level.CONFIG, "event.channel_type = " + _slack.getEvent().getChannel_type());
                logger.log(Level.CONFIG, "event.blocks = " + _slack.getEvent().getBlocks());
                if (null != _slack.getEvent().getChannel() && null != _slack.getEvent().getUser()
                        && _slack.getEvent().getChannel().equals(System.getenv("SLACK_TARGET_CHANNEL"))
                        && _slack.getEvent().getUser().equals(System.getenv("SLACK_TARGET_USER"))) {
                    LineMessagingApiMessage message = new LineMessagingApiMessage();
                    message.setType("text");
                    message.setText(_slack.getEvent().getText());
                    List<LineMessagingApiMessage> messages = new ArrayList<>(0);
                    messages.add(message);
                    line = new LineMessagingApi();
                    line.setTo(System.getenv("LINE_TARGET_GROUP_ID"));
                    line.setNotificationDisabled(false);
                    line.setMessages(messages);
                }
            }
        }
        return line;
    }
}