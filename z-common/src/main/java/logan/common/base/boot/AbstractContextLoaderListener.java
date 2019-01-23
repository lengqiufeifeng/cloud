package logan.common.base.boot;

import org.apache.commons.lang3.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/**
 * @Desc：自定义 ContextLoaderListener 用于输出系统运行环境，以及启动通知事件 也可初始化系统参数，推荐使用@PostConstruct
 * @Title：AbstractContextLoaderListener
 * @address:
 * @author: logan extends ContextLoaderListener
 * @version: 1.0
 * @date: 2016年11月2日 下午1:32:23
 */
public abstract class AbstractContextLoaderListener {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 系统名称
     *
     * @return
     */
    public abstract String getCallName();

    /**
     * 是否初始化
     * super.contextInitialized(event);
     *
     * @return
     */
    public abstract boolean isContextInitialized();


    protected void printStart() {
        logger.info("------------------------------------------------" );
        logger.info("启动 {} ..", getCallName());
        logger.info("------------------------------------------------" );
        @SuppressWarnings("serial" )
        Map<String, Object> map = new LinkedHashMap<String, Object>() {
            @Override
            public String toString() {
                int maxKeyLength = 0;
                for (String key : keySet()) {
                    int len = key.length();
                    if (maxKeyLength < len) {
                        maxKeyLength = len;
                    }
                }
                String fmt = "%" + maxKeyLength + "s : %s";
                StringBuilder sb = new StringBuilder(128);
                for (Map.Entry<String, Object> entry : entrySet()) {
                    if (sb.length() != 0) {
                        sb.append('\n');
                    }
                    sb.append(String.format(fmt, entry.getKey(), String.valueOf(entry.getValue())));
                }
                return sb.toString();
            }

        };
        // 收集系统环境数据
        getCPUInfo(map);
        getMemoryInfo(map);
        getJVMInfo(map);
        logger.info("系统工作环境:\n{}", map.toString());
        logger.info("系统工作目录: {}", SystemUtils.USER_DIR);
        logger.info("系统默认编码: {}", Charset.defaultCharset().name());
        logger.info("系统默认时区: {}, {}", TimeZone.getDefault().getID(), Locale.getDefault().toString());
    }

    private static void getCPUInfo(Map<String, Object> map) {
        map.put("Avaible CPU(s)", Runtime.getRuntime().availableProcessors());
        map.put("Processor(s) Identifier", System.getenv("PROCESSOR_IDENTIFIER" ));

    }

    private void getMemoryInfo(Map<String, Object> map) {
        double max = Runtime.getRuntime().maxMemory() / 1024 / 1024;
        double allocated = Runtime.getRuntime().totalMemory() / 1024 / 1024;
        double nonAllocated = max - allocated;
        double cached = Runtime.getRuntime().freeMemory() / 1024 / 1024;
        double used = allocated - cached; // really used memory
        double useable = max - used; // allocated, but non-used and
        // non-allocated memory
        DecimalFormat df1 = new DecimalFormat(" (0.00'%')" );
        DecimalFormat df2 = new DecimalFormat("# 'MB'" );
        map.put("Allowed Memory", df2.format(max));
        map.put("Allocated Memory", df2.format(allocated) + df1.format(allocated / max * 100));
        map.put("Non-Allocated Memory", df2.format(nonAllocated) + df1.format(nonAllocated / max * 100));
        map.put("Allocated Memory", df2.format(allocated));
        map.put("Used Memory", df2.format(used) + df1.format(used / max * 100));
        map.put("Unused (cached) Memory", df2.format(cached) + df1.format(cached / max * 100));
        map.put("Useable Memory", df2.format(useable) + df1.format(useable / max * 100));
    }

    private void getJVMInfo(Map<String, Object> map) {
        map.put("JVM Vendor", SystemUtils.JAVA_VM_VENDOR);
        map.put("JVM Name", SystemUtils.JAVA_VM_NAME);
        map.put("JVM Version", SystemUtils.JAVA_VERSION);
        map.put("JVM Home", SystemUtils.JAVA_HOME);
        map.put("OS Name", SystemUtils.OS_NAME);
        map.put("OS Version", SystemUtils.OS_VERSION);
        map.put("OS Architecture", SystemUtils.OS_ARCH);
    }

    protected void printStarted() throws Exception {
        // throw new Exception("启动失败、、、、");
        logger.info("------------------------------------------------" );
        logger.info("启动 {} 成功!", getCallName());
        logger.info("------------------------------------------------" );
    }

    protected void printStop() {
        logger.info("------------------------------------------------" );
        logger.info("正在停止 {} ..", getCallName());
        logger.info("------------------------------------------------" );
    }

    protected void printStopped() {
        logger.info("------------------------------------------------" );
        logger.info("已经停止 {} ! 感谢您使用!", getCallName());
        logger.info("------------------------------------------------" );
    }

    protected void printError(Exception e) {
        logger.error("------------------------------------------------" );
        logger.error("启动 {} 出错! 错误信息如下:", getCallName(), e);
        logger.error("------------------------------------------------" );
    }
}
