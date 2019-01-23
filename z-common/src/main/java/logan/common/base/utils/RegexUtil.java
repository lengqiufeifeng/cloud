package logan.common.base.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Java表单验证工具类
 *
 * @author logan.xu 2015-11-03
 */
public class RegexUtil {

    /**
     * 匹配URL地址
     *
     * @param str
     * @return
     */
    public final static boolean isUrl(String str) {
        return match(str, "^http://([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?$" );
    }

    /**
     * 匹配密码，以字母开头，长度在6-12之间，只能包含字符、数字和下划线。
     *
     * @param str
     * @return
     */
    public final static boolean isPwd(String str) {
        return match(str, "^[a-zA-Z]\\w{6,12}$" );
    }

    /**
     * 验证字符，只能包含中文、英文、数字、下划线等字符。
     *
     * @param str
     * @return
     */
    public final static boolean stringCheck(String str) {
        return match(str, "^[a-zA-Z0-9\u4e00-\u9fa5-_]+$" );
    }

    /**
     * 匹配Email地址
     *
     * @param str
     * @return
     */
    public final static boolean isEmail(String str) {
        return match(str,
                "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$" );
    }

    /**
     * 匹配非负整数（正整数+0）
     *
     * @param str
     * @return
     */
    public final static boolean isInteger(String str) {
        return match(str, "^[+]?\\d+$" );
    }

    /**
     * 判断数值类型，包括整数和浮点数
     *
     * @param str
     * @return
     */
    public final static boolean isNumeric(String str) {
        if (isFloat(str) || isInteger(str))
            return true;
        return false;
    }

    /**
     * 只能输入数字
     *
     * @param str
     * @return
     */
    public final static boolean isDigits(String str) {
        return match(str, "^[0-9]*$" );
    }

    /**
     * 匹配正浮点数
     *
     * @param str
     * @return
     */
    public final static boolean isFloat(String str) {
        return match(str, "^[-\\+]?\\d+(\\.\\d+)?$" );
    }

    /**
     * 联系电话(手机/电话皆可)验证
     *
     * @param text
     * @return
     */
    public final static boolean isTel(String text) {
        if (isMobile(text) || isPhone(text))
            return true;
        return false;
    }

    /**
     * 电话号码验证
     *
     * @param text
     * @return
     */
    public final static boolean isPhone(String text) {
        return match(text, "^(\\d{3,4}-?)?\\d{7,9}$" );
    }

    /**
     * 手机号码验证
     *
     * @param text
     * @return
     */
    public final static boolean isMobile(String text) {
        if (text.length() != 11)
            return false;
        return match(text,
                "^((1[3,5,8][0-9])|(14[5,7])|(17[0,6,7,8]))\\d{8}$" );
    }

    /**
     * 身份证号码验证
     *
     * @param text
     * @return
     */
    public final static boolean isIdCardNo(String text) {
        return match(text,
                "(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])" );
    }

    /**
     * 邮政编码验证
     *
     * @param text
     * @return
     */
    public final static boolean isZipCode(String text) {
        return match(text, "^[0-9]{6}$" );
    }


    /**
     * 判断是否为合法字符(a-zA-Z0-9-_)
     *
     * @param text
     * @return
     */
    public final static boolean isRightfulString(String text) {
        return match(text, "^[A-Za-z0-9_-]+$" );
    }

    /**
     * 判断英文字符(a-zA-Z)
     *
     * @param text
     * @return
     */
    public final static boolean isEnglish(String text) {
        return match(text, "^[A-Za-z]+$" );
    }

    /**
     * 判断中文字符(包括汉字和符号)
     *
     * @param text
     * @return
     */
    public final static boolean isChineseChar(String text) {
        return match(text, "^[\u0391-\uFFE5]+$" );
    }

    /**
     * 匹配汉字
     *
     * @param text
     * @return
     */
    public final static boolean isChinese(String text) {
        return match(text, "^[\u4e00-\u9fa5]+$" );
    }

    /**
     * 是否包含中英文特殊字符，除英文"-_"字符外
     *
     * @param str
     * @return
     */
    public static boolean isContainsSpecialChar(String text) {
        if (StringUtils.isBlank(text))
            return false;
        String[] chars = {"[", "`", "~", "!", "@", "#", "$", "%", "^", "&", "*",
                "(", ")", "+", "=", "|", "{", "}", "'", ":", ";", "'", ",", "[",
                "]", ".", "<", ">", "/", "?", "~", "！", "@", "#", "￥", "%", "…",
                "&", "*", "（", "）", "—", "+", "|", "{", "}", "【", "】", "‘", "；",
                "：", "”", "“", "’", "。", "，", "、", "？", "]"};
        for (String ch : chars) {
            if (text.contains(ch))
                return true;
        }
        return false;
    }

    /**
     * 过滤中英文特殊字符，除英文"-_"字符外
     *
     * @param text
     * @return
     */
    public static String stringFilter(String text) {
        String regExpr = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regExpr);
        Matcher m = p.matcher(text);
        return m.replaceAll("" ).trim();
    }

    /**
     * 过滤html代码
     *
     * @param inputString 含html标签的字符串
     * @return
     */
    public static String htmlFilter(String inputString) {
        String htmlStr = inputString; // 含html标签的字符串
        String textStr = "";
        Pattern p_script;
        Matcher m_script;
        Pattern p_style;
        Matcher m_style;
        Pattern p_html;
        Matcher m_html;
        Pattern p_ba;
        Matcher m_ba;

        try {
            String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
            // }
            String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
            // }
            String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
            String patternStr = "\\s+";

            p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
            m_script = p_script.matcher(htmlStr);
            htmlStr = m_script.replaceAll("" ); // 过滤script标签

            p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
            m_style = p_style.matcher(htmlStr);
            htmlStr = m_style.replaceAll("" ); // 过滤style标签

            p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
            m_html = p_html.matcher(htmlStr);
            htmlStr = m_html.replaceAll("" ); // 过滤html标签

            p_ba = Pattern.compile(patternStr, Pattern.CASE_INSENSITIVE);
            m_ba = p_ba.matcher(htmlStr);
            htmlStr = m_ba.replaceAll("" ); // 过滤空格

            textStr = htmlStr;

        } catch (Exception e) {
            System.err.println("Html2Text: " + e.getMessage());
        }
        return textStr;// 返回文本字符串
    }

    /**
     * 正则表达式匹配
     *
     * @param text 待匹配的文本
     * @param reg  正则表达式
     * @return
     */
    private final static boolean match(String text, String reg) {
        if (StringUtils.isEmpty(text) || StringUtils.isBlank(reg))
            return false;
        return Pattern.compile(reg).matcher(text).matches();
    }

}
