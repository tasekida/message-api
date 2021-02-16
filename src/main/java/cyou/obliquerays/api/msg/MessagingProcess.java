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
import java.nio.file.Path;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.sun.net.httpserver.HttpServer;

import cyou.obliquerays.api.msg.line.webhook.LineWebhookHandler;
import cyou.obliquerays.api.msg.slack.webhook.SlackReceivingWebhookHandler;
import cyou.obliquerays.status.LockFileStatus;

/**
 * メッセージ交換用APIの永続起動
 */
public class MessagingProcess {
    /** ロガー */
    private static final Logger logger = Logger.getLogger(MessagingProcess.class.getName());

    private ExecutorService executorService = Executors.newWorkStealingPool(3);

    public MessagingProcess() throws IOException {
		Path lockFile = Path.of(this.getClass().getSimpleName() + ".lock");
    	try {
			LockFileStatus lockFileStatus =
					new LockFileStatus(Thread.currentThread(), lockFile);
			executorService.execute(lockFileStatus);
		} catch (IOException e) {
			logger.log(Level.SEVERE, "プロセス実行時存在ファイルの管理に失敗#" + lockFile, e);
			throw e;
		}
    }

    /**
     * メッセージAPIサーバー起動
     * @throws IOException サーバー起動失敗
     * @throws NoSuchAlgorithmException 暗号アルゴリズムの未サポート
     * @throws KeyManagementException SSLContextの初期化に失敗
     */
    private void startServer() throws IOException, NoSuchAlgorithmException, KeyManagementException {
        HttpServer server = null;
        try {
            server = HttpServer.create(new InetSocketAddress(8080), 0);
            // SlackWebhook
            server.createContext(System.getenv("SLACK_WEBHOOK_CONTEXT"), new SlackReceivingWebhookHandler());
            // LineWebhook
            server.createContext(System.getenv("LINE_WEBHOOK_CONTEXT"), new LineWebhookHandler());
            server.setExecutor(this.executorService);
            server.start();
            logger.log(Level.INFO, "サーバー起動");
            while (true) {
                TimeUnit.SECONDS.sleep(60L);
            }
        } catch (IOException e) {
        	logger.log(Level.SEVERE, "サーバー起動失敗", e);
        	throw e;
		} catch (KeyManagementException e) {
        	logger.log(Level.SEVERE, "SSLContextの初期化に失敗", e);
        	throw e;
		} catch (NoSuchAlgorithmException e) {
			logger.log(Level.SEVERE, "暗号アルゴリズムの未サポート", e);
			throw e;
		} catch (InterruptedException e) {
        	logger.log(Level.WARNING, "サーバー継続不可", e);
        } finally {
            if (null != server) {
                server.stop(0);
            }
            logger.log(Level.INFO, "サーバー停止");
        }
    }

    /**
     * メッセージ交換用APIのエントリーポイント
     * @param args プロセスの引数
     */
    public static void main(String[] args) {
    	int returnCode = 1;
    	try (InputStream resource = ClassLoader.getSystemResourceAsStream("logging.properties")) {
            LogManager.getLogManager().readConfiguration(resource);
            MessagingProcess process = new MessagingProcess();
            process.startServer();
            returnCode = 0;
        } catch (Throwable t) {
        	logger.log(Level.SEVERE, "エラー終了", t);
        }
        logger.log(Level.CONFIG, "停止直前#returnCode=" + returnCode);
        System.exit(returnCode);
    }
}
