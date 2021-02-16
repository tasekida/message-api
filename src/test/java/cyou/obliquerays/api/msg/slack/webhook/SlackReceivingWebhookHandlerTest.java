/**
 *  Copyright 2021 tasekida
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
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.json.bind.Jsonb;

import org.apache.johnzon.jsonb.JohnzonBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cyou.obliquerays.api.msg.slack.model.SlackReceivingWebhook;

/**
 * SlackReceivingWebhookHandlerのUnitTest
 */
class SlackReceivingWebhookHandlerTest {

	private static final Logger logger = Logger.getLogger(SlackReceivingWebhookHandlerTest.class.getName());

	/** @throws java.lang.Exception */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
    	try (InputStream resource = ClassLoader.getSystemResourceAsStream("logging.properties")) {
            LogManager.getLogManager().readConfiguration(resource);
        } catch (Throwable t) {
        	logger.log(Level.SEVERE, "エラー終了", t);
        }
	}

	/** @throws java.lang.Exception */
	@AfterAll
	static void tearDownAfterClass() throws Exception {}

	/** @throws java.lang.Exception */
	@BeforeEach
	void setUp() throws Exception {}

	/** @throws java.lang.Exception */
	@AfterEach
	void tearDown() throws Exception {}

	/**
	 * {@link cyou.obliquerays.api.msg.slack.webhook.SlackReceivingWebhookHandler#handle(com.sun.net.httpserver.HttpExchange)} のためのテスト・メソッド。
	 */
	@Test
	void testSlackToLine() {
        Path input = Path.of(
        		"src/test/resources"
        		, this.getClass().getCanonicalName().replaceAll("\\.", "/").toLowerCase()
        		, Thread.currentThread().getStackTrace()[1].getMethodName()
        		, "body.json").toAbsolutePath().normalize();

		try (BufferedReader reader = Files.newBufferedReader(input);
				Jsonb jsonb = new JohnzonBuilder().build();) {
            SlackReceivingWebhook slack = jsonb.fromJson(reader, SlackReceivingWebhook.class);
            if (null != slack) {
                logger.log(Level.CONFIG, "token = " + slack.getToken());
                logger.log(Level.CONFIG, "team_id = " + slack.getTeam_id());
                logger.log(Level.CONFIG, "api_app_id = " + slack.getApi_app_id());
                logger.log(Level.CONFIG, "type = " + slack.getType());
                logger.log(Level.CONFIG, "authed_users = " + slack.getAuthed_users());
                logger.log(Level.CONFIG, "event_id = " + slack.getEvent_id());
                logger.log(Level.CONFIG, "event_time = " + slack.getEvent_time());
                if (null != slack.getEvent()) {
                    logger.log(Level.CONFIG, "event.type = " + slack.getEvent().getType());
                    logger.log(Level.CONFIG, "event.event_ts = " + slack.getEvent().getEvent_ts());
                    logger.log(Level.CONFIG, "event.user = " + slack.getEvent().getUser());
                    logger.log(Level.CONFIG, "event.ts = " + slack.getEvent().getTs());
                    logger.log(Level.CONFIG, "event.item = " + slack.getEvent().getItem());
                    logger.log(Level.CONFIG, "event.client_msg_id = " + slack.getEvent().getClient_msg_id());
                    logger.log(Level.CONFIG, "event.text = " + slack.getEvent().getText());
                    logger.log(Level.CONFIG, "event.team = " + slack.getEvent().getTeam());
                    logger.log(Level.CONFIG, "event.channel = " + slack.getEvent().getChannel());
                    logger.log(Level.CONFIG, "event.channel_type = " + slack.getEvent().getChannel_type());
                    logger.log(Level.CONFIG, "event.blocks = " + slack.getEvent().getBlocks());
                }
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "JSON変換失敗", e);
        }
	}
}
