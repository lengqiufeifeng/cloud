package logan.common.base.utils.http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author 冷秋飞枫 qq：425018553 http编程 助手 {@link HttpClientUtil}
 */
@Deprecated
public class Httphandler {
    static private String str = null;
    static private String QEncode = "gbk";
    static private String BEncode = "gbk";

    /**
     * httget 请求
     *
     * @param url
     * @param mapdata Map key=val 对应
     * @param qencode 请求 编码
     * @param bencode 返回编码
     * @return 服务器返回字符串
     * @throws ClientProtocolException
     * @throws IOException
     */
    static public String myhttpGet(String url, Map<String, String> mapdata, String qencode, String bencode)
            throws ClientProtocolException, IOException {
        str = url;
        QEncode = qencode;
        BEncode = bencode;

        HttpClient client = new DefaultHttpClient();
//		HttpHost proxy = new HttpHost("127.0.0.1",8888);  
//		client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,proxy);

        StringBuffer sbu = new StringBuffer();
        if (mapdata != null) {
            // 反射 数据
            Set<Map.Entry<String, String>> set = mapdata.entrySet();
            for (Iterator<Map.Entry<String, String>> it = set.iterator(); it.hasNext(); ) {
                Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
                sbu.append(entry.getKey() + "=" + URLEncoder.encode(entry.getValue(), QEncode) + "&" );

            } // end
            System.out.print(sbu.toString());
            str = str + "?" + sbu.toString();
        }
        HttpGet getUrl = new HttpGet(str);
        HttpResponse response = null;

        response = client.execute(getUrl);
        str = requseturl(response);

        return str;
    }

    /**
     * httpost 请求
     *
     * @param url
     * @param mapdata Map key=val 对应
     * @param qencode 请求 编码
     * @param bencode 返回编码
     * @return 服务器返回字符串
     * @throws ClientProtocolException
     * @throws IOException
     */
    static public String myhttpPost(String url, Map<String, String> mapdata, String encode, String bencode)
            throws ClientProtocolException, IOException {
        QEncode = encode;
        BEncode = bencode;
        HttpClient client = new DefaultHttpClient();
        HttpPost postUrl = new HttpPost(url);
        ArrayList<BasicNameValuePair> pairs = new ArrayList<BasicNameValuePair>();
        if (mapdata != null) {
            // 反射 数据
            Set<Map.Entry<String, String>> set = mapdata.entrySet();
            for (Iterator<Map.Entry<String, String>> it = set.iterator(); it.hasNext(); ) {
                Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();

                pairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            } // end
        }
        UrlEncodedFormEntity p_entity = new UrlEncodedFormEntity(pairs, QEncode); // 编码
        /*
         * 将POST数据放入HTTP请求
         */
        postUrl.setEntity(p_entity);
        HttpResponse response = client.execute(postUrl);
        System.out.print(p_entity.getContent());
        return requseturl(response);
    }

    /**
     * 返回数据量 转化字符串
     *
     * @param response
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    static String requseturl(HttpResponse response) throws IllegalStateException, IOException {
        System.out.println(response.getStatusLine().getStatusCode());
        if (response.getStatusLine().getStatusCode() == 200)// 连接成功
        {
            HttpEntity entity = response.getEntity();
            InputStream content = entity.getContent();
            String returnConnection = convertStreamToString(content);
            return returnConnection;
        }
        return "";
    }

    /**
     * 字符编码
     *
     * @param is
     * @return
     * @throws UnsupportedEncodingException
     */
    static private String convertStreamToString(InputStream is) throws UnsupportedEncodingException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, BEncode));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
