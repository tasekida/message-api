/**
 *
 */
package cyou.obliquerays.status;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author oblique-rays
 */
public class LockFileStatus extends Thread {
    /** ロガー */
    private static final Logger logger = Logger.getLogger(LockFileStatus.class.getName());

    /** プロセス実行時存在ファイル */
    private final Path lockFile;

    /** プロセス実行時存在ファイル監視鍵 */
    private final WatchKey watchKey;

    /** メインスレッド */
    private final Thread mainThread;

	/**
	 * コンストラクター
	 * @throws IOException
	 */
	public LockFileStatus(Thread _thread, Path _lockFile) throws IOException {
		logger.log(Level.CONFIG, "開始");
		this.mainThread = Objects.requireNonNull(_thread);
		this.lockFile = Objects.requireNonNull(_lockFile).toAbsolutePath().normalize();

		Files.createFile(this.lockFile, PosixFilePermissions.asFileAttribute(
				Set.of(PosixFilePermission.OWNER_READ
						,PosixFilePermission.OTHERS_WRITE
						,PosixFilePermission.OWNER_EXECUTE)));
		logger.log(Level.INFO, "プロセス実行時存在ファイル作成#" + this.lockFile);

		WatchService watchService = FileSystems.getDefault().newWatchService();
		this.watchKey = this.lockFile.getParent().register(watchService, StandardWatchEventKinds.ENTRY_DELETE);
		logger.log(Level.INFO, "プロセス実行時存在ファイル監視鍵取得#" + this.lockFile.getParent());

		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			logger.log(Level.CONFIG, "シャットダウンフック開始");
			try {
				if (Files.deleteIfExists(this.lockFile))
					logger.log(Level.INFO, "プロセス実行時存在ファイル削除#" + this.lockFile);
			} catch (IOException e) {
				logger.log(Level.SEVERE, "プロセス実行時存在ファイル削除失敗#" + this.lockFile, e);
			}
			logger.log(Level.CONFIG, "シャットダウンフック終了");
		}));
		logger.log(Level.INFO, "プロセス実行時存在ファイル削除用シャットダウンフック登録");

		logger.log(Level.CONFIG, "終了");
	}

	@Override
	public void run() {
		logger.log(Level.CONFIG, "開始");
		try {
			while (this.watchKey.isValid()) {
				this.watchKey.pollEvents().stream()
				.filter((watchEvent) -> watchEvent.kind() == StandardWatchEventKinds.ENTRY_DELETE)
				.filter((watchEvent) -> watchEvent.context().toString().equals(lockFile.getFileName().toString()))
				.forEach((watchEvent) -> {
					logger.log(Level.INFO, "プロセス実行時存在ファイルの削除検知#" + watchEvent.context().toString());
					this.watchKey.cancel();
					this.mainThread.interrupt();
				});
				TimeUnit.SECONDS.sleep(1L);
			}
		} catch (InterruptedException e) {
			logger.log(Level.SEVERE, "待機失敗", e);
			this.mainThread.interrupt();
		}
		logger.log(Level.CONFIG, "終了");
	}
}