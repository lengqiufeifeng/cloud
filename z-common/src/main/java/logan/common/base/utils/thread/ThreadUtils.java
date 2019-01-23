package logan.common.base.utils.thread;

/**
 * 线程工具
 *
 * @author Administrator
 */
public abstract class ThreadUtils {
    public static void sleepShortTime() {
        sleep(1L);
    }

    public static void sleep(long millis) {
        if (millis > 0L) {
            try {
                Thread.sleep(millis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
