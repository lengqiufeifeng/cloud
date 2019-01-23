package logan.common.base.utils;

import java.text.*;
import java.util.Calendar;

/**
 * @Desc：ID生成工具
 * @Title：IDUtil
 * @address: http://www.liveneo.com.cn/
 * @author: zhaoyy
 * @version: 1.0
 * @date: 2016年11月2日 下午6:02:40
 */
public final class IDUtil {
    private static final FieldPosition HELPER_POSITION = new FieldPosition(0);
    private final static Format dateFormat = new SimpleDateFormat("yyyyMMddHHmmss" );
    private final static NumberFormat numberFormat = new DecimalFormat("0000" );
    private static int seq = 0;
    private static final int MAX = 9999;

    private IDUtil() {
    }

    ;

    /**
     * @return
     * @Desc: 按照时间格式生成序列ID
     * @Author: zhaoyy 2016年11月2日 下午6:03:01
     */
    public static synchronized String generateId() {
        Calendar rightNow = Calendar.getInstance();
        StringBuffer sb = new StringBuffer();
        dateFormat.format(rightNow.getTime(), sb, HELPER_POSITION);
        numberFormat.format(seq, sb, HELPER_POSITION);
        if (seq == MAX) {
            seq = 0;
        } else {
            seq++;
        }
        return sb.toString();
    }

//    public static void main(String[] args) {
//        for (int i = 0; i < 10000; i++) {
//            System.out.println(generateId());
//        }
//    }
}
