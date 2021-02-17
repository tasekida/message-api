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
package cyou.obliquerays.status;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * LockFileStatusのUnitTest
 */
class LockFileStatusTest {

	private static final Logger logger = Logger.getLogger(LockFileStatusTest.class.getName());

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
	 * {@link cyou.obliquerays.status.LockFileStatus#LockFileStatus(java.lang.Thread, java.nio.file.Path)} のためのテスト・メソッド。
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@Test
	void testLockFileStatus() throws IOException, InterruptedException {
		Path lockFile = Path.of(this.getClass().getSimpleName() + ".lock");
    	try {
			LockFileStatus lockFileStatus =
					new LockFileStatus(Thread.currentThread(), lockFile);
			CompletableFuture<Void> status = CompletableFuture.runAsync(lockFileStatus, Executors.newSingleThreadExecutor());
			Files.delete(lockFile);
			while (!status.isCompletedExceptionally() || !status.isDone() || !status.isCancelled()) {
				status.join();
				TimeUnit.SECONDS.sleep(1L);
			}
		} catch (IOException e) {
			logger.log(Level.SEVERE, "プロセス実行時存在ファイルの管理に失敗#" + lockFile, e);
			throw e;
		} catch (InterruptedException e) {
			logger.log(Level.WARNING, "Threadへの割り込みを検知#" + lockFile, e);
		}
	}

}
