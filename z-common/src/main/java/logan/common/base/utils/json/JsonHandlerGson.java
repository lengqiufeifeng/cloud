package logan.common.base.utils.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

import java.lang.reflect.Type;

/**
 * JSON序列化辅助类
 **/
public class JsonHandlerGson {
    private final static Gson gosn = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss" ).create();
    JsonParser jp = new JsonParser();

    public static String ToJsonStr(Object obj) {
        return gosn.toJson(obj);
    }

    public static String ToJsonStrFormat(Object obj) {
        return JsonFormatTool.formatJson(ToJsonStr(obj));
    }

    /**
     * json字符串转类
     *
     * @param json
     * @param classOfT
     * @return
     */
    public static <T> T formJsonStr(String json, Class<T> classOfT) {
        return gosn.fromJson(json, classOfT);
    }

    /**
     * json字符串转集合
     *
     * @param json     Type t=new TypeToken<List<String>>(){}.getType();
     * @param classOfT
     * @return
     */
    public static <T> T formJsonStr(String json, Type typeOfT) {
        return gosn.fromJson(json, typeOfT);
    }
}