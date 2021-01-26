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
package cyou.obliquerays.api.msg.line.api;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.SSLContext;

import cyou.obliquerays.api.msg.line.model.LineMessagingApi;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

/**
 * このアプリケーションからLineへメッセージを送信する処理
 * @see <a href="https://developers.line.biz/ja/reference/messaging-api/">Line Messaging API</a>
 */
public class LineMessagingAPIHandler {
    //// Fields
    /**
     * ロガー
     */
    private static final Logger logger = Logger.getLogger(LineMessagingAPIHandler.class.getName());

    /**
     * HTTP送信クライアント
     */
    private final HttpClient client;

    //// Constructors
    /**
     * デフォルトコンストラクター<br>
     * HTTP送信クライアントを初期化
     * @throws NoSuchAlgorithmException TLS1.3は未サポート
     * @throws KeyManagementException SSLContextの初期化に失敗
     */
    public LineMessagingAPIHandler() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext context = SSLContext.getInstance("TLSv1.3");
        context.init(null, null, null);
        this.client = HttpClient.newBuilder()
                                .sslContext(context)
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
     * @param _message Lineへ送信するメッセージのオブジェクト
     * @return Lineへ送信するメッセージの文字列
     */
    public String convertPayload(LineMessagingApi _message) {
        String retStr = "{}";
        try (Jsonb jsonb = JsonbBuilder.create()) {
            retStr = jsonb.toJson(_message);
        } catch (Exception e) {
            logger.log(Level.INFO, "SlackIncomingWebhookApiへの送信情報作成に失敗", e);
        }
        return retStr;
    }

    /**
     * Message メッセージオブジェクトをLineへ送信
     * @param _message Lineへ送信するメッセージのオブジェクト
     */
    public void send(LineMessagingApi _message) {

        String requestBody = this.convertPayload(_message);
        logger.log(Level.CONFIG, "REQUEST = " + requestBody);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(System.getenv("LINE_API_URL")))
                .timeout(Duration.ofMinutes(30))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + System.getenv("LINE_API_TOKEN"))
                .POST(BodyPublishers.ofString(requestBody, StandardCharsets.UTF_8))
                .build();

        this.client.sendAsync(request, BodyHandlers.ofString())
                .thenAccept(response -> {
                    StringBuilder msg = new StringBuilder().append("code=").append(response.statusCode()).append(", body=").append(response.body());
                    if (response.statusCode() == HttpURLConnection.HTTP_OK) {
                        logger.log(Level.INFO, "LineMessagingAPIへの送信に成功: RESPONSE = " + msg.toString());
                    } else {
                        logger.log(Level.WARNING, "LineMessagingAPIへの送信に失敗: RESPONSE = " + msg.toString());
                    }
                })
                .exceptionally(e -> {
                    logger.log(Level.WARNING, "LineMessagingAPIへの送信に失敗", e);
                    return null;
                });
    }
}
