package logan.common.base.utils.json;

/**
 * Created by logan on 2017/9/26.
 * qq：425018553
 */
public class JsonFormatTool {
    /**
     * 单位缩进字符串。
     */
    private static String SPACE = "   ";

    /**
     * 返回格式化JSON字符串。
     *
     * @param json 未格式化的JSON字符串。
     * @return 格式化的JSON字符串。
     */
    public static String formatJson(String json) {
        StringBuffer result = new StringBuffer();
        int length = json.length();
        int number = 0;
        char key = 0;
        //遍历输入字符串。
        for (int i = 0; i < length; i++) {
            //获取当前字符。
            key = json.charAt(i);
            if ((key == '[') || (key == '{')) {
                result.append(key);
                result.append('\n');//换行
                number++;
                result.append(indent(number));
                //进行下一次循环。
                continue;
            }
            //先判断  },  ],
            if ((key == ']') || (key == '}')) {
                result.append('\n');
                //缩进。
                number--;
                result.append(indent(number));
                //当前字符。
                result.append(key);
                //继续下一次循环。
                continue;
            }
            if ((key == ',')) {
                result.append(key);
                result.append('\n');
                result.append(indent(number));
                continue;
            }
            result.append(key);
        }
        return result.toString();
    }

    /**
     * 返回指定次数的缩进字符串。每一次缩进三个空格，即SPACE。
     *
     * @param number 缩进次数。
     * @return 指定缩进次数的字符串。
     */
    private static String indent(int number) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < number; i++) {
            result.append(SPACE);
        }
        return result.toString();
    }
}
