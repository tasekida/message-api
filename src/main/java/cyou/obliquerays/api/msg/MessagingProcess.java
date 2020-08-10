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
package cyou.obliquerays.api.msg;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.sun.net.httpserver.HttpServer;

import cyou.obliquerays.api.msg.line.webhook.LineWebhookHandler;
import cyou.obliquerays.api.msg.slack.webhook.SlackReceivingWebhookHandler;

/**
 * メッセージAPIプロセスのエントリーポイント
 */
public class MessagingProcess {
    //// Fields
    /**
     * ロガー
     */
    private static final Logger logger = Logger.getLogger(MessagingProcess.class.getName());

    //// Methods
    /**
     * メッセージAPIサーバー起動
     */
    private void startServer() {
        HttpServer server = null;
        try {
            server = HttpServer.create(new InetSocketAddress(8080), 0);
            // SlackWebhook
            server.createContext(System.getenv("SLACK_WEBHOOK_CONTEXT"), new SlackReceivingWebhookHandler());
            // LineWebhook
            server.createContext(System.getenv("LINE_WEBHOOK_CONTEXT"), new LineWebhookHandler());
            server.setExecutor(Executors.newWorkStealingPool());
            server.start();
            while (true) {
                TimeUnit.SECONDS.sleep(30);
            }
        } catch (Exception e) {
            server.stop(0);
            logger.log(Level.SEVERE, "サーバー停止", e);
        }
    }

    //// Entry Point
    /**
     * メッセージAPIプロセスのエントリーポイント
     * @param args プロセスの引数
     * @throws IOException リソースからlogging.propertiesの読み取りに失敗
     */
    public static void main(String[] args) throws IOException {
        try (InputStream resource = ClassLoader.getSystemResourceAsStream("logging.properties")) {
            LogManager.getLogManager().readConfiguration(resource);
        }
        MessagingProcess process = new MessagingProcess();
        process.startServer();
    }
}
