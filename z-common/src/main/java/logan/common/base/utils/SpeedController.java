package logan.common.base.utils;


import logan.common.base.utils.thread.ThreadUtils;

public class SpeedController {
    private long now = 0L;
    private long timestamp = 0L;
    private final long interval;

    public SpeedController(long interval) {
        this.interval = interval;
    }

    public void sleepIfNeed() {
        this.now = System.currentTimeMillis();
        if (this.timestamp == 0L) {
            this.timestamp = this.now;
            return;
        }
        if (this.timestamp > this.now) {
            ThreadUtils.sleep(this.interval);
            this.timestamp = System.currentTimeMillis();
            return;
        }
        long requiredSleepingTime = this.timestamp + this.interval - this.now;
        if (requiredSleepingTime > 0L) {
            ThreadUtils.sleep(requiredSleepingTime);
        }
        this.timestamp = System.currentTimeMillis();
    }
}
