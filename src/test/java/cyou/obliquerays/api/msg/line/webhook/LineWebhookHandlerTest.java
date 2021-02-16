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
package cyou.obliquerays.api.msg.line.webhook;

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

import cyou.obliquerays.api.msg.line.model.LineWebhook;
import cyou.obliquerays.api.msg.line.model.LineWebhookEvent;

/**
 * LineWebhookHandlerのUnitTest
 */
class LineWebhookHandlerTest {

	private static final Logger logger = Logger.getLogger(LineWebhookHandlerTest.class.getName());

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
	 * {@link cyou.obliquerays.api.msg.line.webhook.LineWebhookHandler#lineToSlack(cyou.obliquerays.api.msg.line.model.LineWebhook)} のためのテスト・メソッド。
	 */
	@Test
	public void testLineToSlack() {
        Path input = Path.of(
        		"src/test/resources"
        		, this.getClass().getCanonicalName().replaceAll("\\.", "/").toLowerCase()
        		, Thread.currentThread().getStackTrace()[1].getMethodName()
        		, "body.json").toAbsolutePath().normalize();

		try (BufferedReader reader = Files.newBufferedReader(input);
				Jsonb jsonb = new JohnzonBuilder().build();) {
			LineWebhook line = jsonb.fromJson(reader, LineWebhook.class);
	        if (null != line) {
	            logger.log(Level.CONFIG, "destination = " + line.getDestination());
	            for (LineWebhookEvent event : line.getEvents()) {
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
	                    break;
	                default:
	                    logger.log(Level.CONFIG, "event.type = " + event.getType());
	                    logger.log(Level.CONFIG, "event.mode = " + event.getMode());
	                    logger.log(Level.CONFIG, "event.timestamp = " + event.getTimestamp());
	                }
	            }
	        }
		} catch (Exception e) {
            logger.log(Level.SEVERE, "JSON変換失敗", e);
		}
	}
}
