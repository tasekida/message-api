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
package cyou.obliquerays.api.msg.slack.api;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.json.bind.Jsonb;

import org.apache.johnzon.jsonb.JohnzonBuilder;

import cyou.obliquerays.api.msg.slack.model.SlackIncomingWebhookPayload;

/**
 * このアプリケーションからSlackへメッセージを送信する処理
 * @see <a href="https://api.slack.com/messaging/webhooks">Slack Incoming Webhooks</a>
 */
public class SlackIncomingWebhook {
    //// Fields
    /**
     * ロガー
     */
    private static final Logger logger = Logger.getLogger(SlackIncomingWebhook.class.getName());
    /**
     * HTTP送信クライアント
     */
    private final HttpClient client;

    //// Constructors
    /**
     * デフォルトコンストラクター<br>
     * HTTP送信クライアントを初期化
     */
    public SlackIncomingWebhook() {
        this.client = HttpClient.newBuilder()
                .version(Version.HTTP_2)
                .followRedirects(Redirect.NORMAL)
                .connectTimeout(Duration.ofSeconds(30))
                .proxy(HttpClient.Builder.NO_PROXY)
                .executor(Executors.newWorkStealingPool())
                .build();
    }

    //// Methods
    /**
     * Message オブジェクトを変換
     * @param _message Slackへ送信するメッセージのオブジェクト
     * @return Slackへ送信するメッセージの文字列
     */
    public String convertPayload(SlackIncomingWebhookPayload _message) {
        String retStr = "{}";
        try (Jsonb jsonb = new JohnzonBuilder().build()) {
            retStr = jsonb.toJson(_message);
        } catch (Exception e) {
            logger.log(Level.INFO, "SlackIncomingWebhookApiへの送信情報作成に失敗", e);
        }
        return retStr;
    }

    /**
     * Message メッセージオブジェクトをSlackへ送信
     * @param _message Slackへ送信するメッセージのオブジェクト
     */
    public void send(SlackIncomingWebhookPayload _message) {

        String requestBody = this.convertPayload(_message);
        logger.log(Level.CONFIG, "REQUEST = " + requestBody);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(System.getenv("SLACK_API_URL")))
                .timeout(Duration.ofMinutes(30))
                .header("Content-Type", "application/json")
                .POST(BodyPublishers.ofString(requestBody, StandardCharsets.UTF_8))
                .build();

        this.client.sendAsync(request, BodyHandlers.ofString())
                .thenAccept(response -> {
                    StringBuilder msg = new StringBuilder().append("code=").append(response.statusCode()).append(", body=").append(response.body());
                    if (response.statusCode() == HttpURLConnection.HTTP_OK) {
                        logger.log(Level.INFO, "SlackIncomingWebhookApiへの送信に成功: RESPONSE = " + msg.toString());
                    } else {
                        logger.log(Level.WARNING, "SlackIncomingWebhookApiへの送信に失敗: RESPONSE = " + msg.toString());
                    }
                })
                .exceptionally(e -> {
                    logger.log(Level.WARNING, "SlackIncomingWebhookApiへの送信に失敗", e);
                    return null;
                });
    }
}
