package logan.common.base.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 汉字转换拼音
 *
 * @author user
 * @version 1.0.0.0
 * @date 2015-5-21
 */
public class HZ2PY {
    static HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();

    static {
        // UPPERCASE：大写 (ZHONG)
        // LOWERCASE：小写 (zhong)
        format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        // WITHOUT_TONE：无音标 (zhong)
        // WITH_TONE_NUMBER：1-4数字表示英标 (zhong4)
        // WITH_TONE_MARK：直接用音标符（必须WITH_U_UNICODE否则异常） (zhòng)
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        // WITH_V：用v表示ü (nv)
        // WITH_U_AND_COLON：用"u:"表示ü (nu:)
        // WITH_U_UNICODE：直接用ü (nü)
        format.setVCharType(HanyuPinyinVCharType.WITH_V);
    }

    /**
     * 输入字符串 转换成拼音
     *
     * @param str
     * @param type
     * @return
     */
    static public String getPY(String str) {
        StringBuffer sb = new StringBuffer();
        if (null != str) {
            char[] srcChar = str.toCharArray();
            for (int i = 0; i < srcChar.length; i++) {
                String[] st = getPY(srcChar[i]);
                if (null == st) {
                    sb.append(srcChar[i]);
                } else {
                    for (int j = 0; j < st.length; j++) {
                        sb.append(st[j]);
                    }
                }

            }

        }
        return sb.toString();
    }

    /**
     * 输入字符串 转换成拼音首字母
     *
     * @param str
     * @param type
     * @return
     */
    static public String getFPY(String str) {
        StringBuffer sb = new StringBuffer();
        if (null != str) {
            char[] srcChar = str.toCharArray();
            for (int i = 0; i < srcChar.length; i++) {
                String[] st = getPY(srcChar[i]);
                if (null == st || st.length == 0) {
//					sb.append(srcChar[i]);
                } else {
                    sb.append(st[0].charAt(0));
                }

            }

        }
        return sb.toString();
    }

    public static String[] getPY(char c) {
        try {
            String[] pinyin = PinyinHelper.toHanyuPinyinStringArray(c, format);
            return pinyin;
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
        return null;
    }
}
