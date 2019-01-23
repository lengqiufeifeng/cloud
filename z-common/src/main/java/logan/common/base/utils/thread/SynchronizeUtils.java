package logan.common.base.utils.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 线程同步工具
 *
 * @author Administrator
 */
public abstract class SynchronizeUtils {
    public static void wait(Object lock) {
        if (lock != null) {
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void wait(Object lock, long timeout) {
        if (lock != null) {
            synchronized (lock) {
                try {
                    lock.wait(timeout);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void notify(Object lock) {
        if (lock != null) {
            synchronized (lock) {
                lock.notify();
            }
        }
    }

    public static void notifyAll(Object lock) {
        if (lock != null) {
            synchronized (lock) {
                lock.notifyAll();
            }
        }
    }

    public static void await(CountDownLatch latch) {
        if (latch != null) {
            try {
                latch.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean await(CountDownLatch latch, long timeout, TimeUnit unit) {
        if (latch != null) {
            try {
                return latch.await(timeout, unit);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public static void countDown(CountDownLatch latch) {
        if (latch != null) {
            latch.countDown();
        }
    }
}
