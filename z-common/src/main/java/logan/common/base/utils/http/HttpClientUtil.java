package logan.common.base.utils.http;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.nio.conn.NHttpClientConnectionManager;
import org.apache.http.nio.conn.NoopIOSessionStrategy;
import org.apache.http.nio.conn.SchemeIOSessionStrategy;
import org.apache.http.nio.conn.ssl.SSLIOSessionStrategy;
import org.apache.http.nio.reactor.ConnectingIOReactor;
import org.apache.http.ssl.SSLContexts;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.net.URI;
import java.nio.charset.Charset;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author logan 2015-11-5
 * @qq 425018553
 */
public class HttpClientUtil {
	private static CloseableHttpAsyncClient closeableHttpAsyncClient;
	private IdleConnectionEvictor connEvictor;

	/**
	 * 异步get请求
	 * 
	 * @param url
	 *            请求地址 不可带参数
	 * @param map
	 *            请求 key value
	 * @param headers
	 *            请求头
	 * @param charset
	 *            编码
	 * @param callfutrue
	 *            回调函数
	 * @return 当回调函数 null 时 返回 否则返回 null
	 * @throws Exception
	 */
	public static HttpResponse httpAsyncGet(String url, Map<String, String> map, Header[] headers, Charset charset,
                                            FutureCallback<HttpResponse> callfutrue) throws Exception {
		URIBuilder uRIBuilder = new URIBuilder(url).setCharset(charset).addParameters(mapToArrNVP(map));
		URI uri = uRIBuilder.build();
		HttpGet httpGet = new HttpGet(uri);
		if (null != headers)
			for (Header hd : headers) {
				httpGet.addHeader(hd);
			}

		Future<HttpResponse> future = HttpClientUtil.instance().execute(httpGet, callfutrue);
		if (null == callfutrue) {
			HttpResponse result = future.get();
			Header ceheader = result.getEntity().getContentEncoding();
			if (ceheader != null) {
				for (HeaderElement element : ceheader.getElements()) {
					if (element.getName().equalsIgnoreCase("gzip")) {
						result.setEntity(new GzipDecompressingEntity(result.getEntity()));
					}
				}
			}
			return result;
		}
		return null;
	}

	/**
	 * 异步post请求
	 * 
	 * @param url
	 *            请求地址 不可带参数
	 * @param map
	 *            请求 key value
	 * @param headers
	 *            请求头
	 * @param charset
	 *            编码
	 * @param callfutrue
	 *            回调函数
	 * @return 当回调函数 null 时 返回 否则返回 null
	 * @throws Exception
	 */
	public static HttpResponse httpAsyncPost(String url, Map<String, String> map, Header[] headers, Charset charset,
                                             FutureCallback<HttpResponse> callfutrue) throws Exception {
		HttpPost httpPost = new HttpPost(url);
		if (null != headers)
			for (Header hd : headers) {
				httpPost.addHeader(hd);
			}
		UrlEncodedFormEntity p_entity = new UrlEncodedFormEntity(mapToArrNVP(map), charset);
		httpPost.setEntity(p_entity);
		Future<HttpResponse> future = HttpClientUtil.instance().execute(httpPost, callfutrue);
		System.out.println("请求地址："+httpPost.getURI());
		if (null == callfutrue) {
			HttpResponse result = future.get();
			Header ceheader = result.getEntity().getContentEncoding();
			if (ceheader != null) {
				for (HeaderElement element : ceheader.getElements()) {
					if (element.getName().equalsIgnoreCase("gzip")) {
						result.setEntity(new GzipDecompressingEntity(result.getEntity()));
					}
				}
			}
			return result;
		}
		return null;
	}

	/**
	 * 异步post请求
	 * 
	 * @param url
	 *            请求地址 不可带参数
	 * @param data
	 *            字符流形式
	 * @param Headers
	 *            请求头
	 * @param charset
	 *            编码
	 * @param callfutrue
	 *            回调函数
	 * @return 当回调函数 null 时 返回 否则返回 null
	 * @throws Exception
	 */
	public static HttpResponse httpAsyncPost(String url, String data, Header[] headers, Charset charset,
                                             FutureCallback<HttpResponse> callfutrue) throws Exception {
		HttpPost httpPost = new HttpPost(url);
		if (null != headers)
			for (Header hd : headers) {
				httpPost.addHeader(hd);
			}
		StringEntity p_entity = new StringEntity(data, charset);
		httpPost.setEntity(p_entity);
		Future<HttpResponse> future = HttpClientUtil.instance().execute(httpPost, callfutrue);
		System.out.println(httpPost.getURI());
		if (null == callfutrue) {
			HttpResponse result = future.get();
			Header ceheader = result.getEntity().getContentEncoding();
			if (ceheader != null) {
				for (HeaderElement element : ceheader.getElements()) {
					if (element.getName().equalsIgnoreCase("gzip")) {
						result.setEntity(new GzipDecompressingEntity(result.getEntity()));
					}
				}
			}
			return result;
		}
		return null;
	}

	public static CloseableHttpAsyncClient instance() throws Exception {
		if (null == closeableHttpAsyncClient) {
			closeableHttpAsyncClient = new HttpClientUtil().getHttpAsyncClient();
			System.out.println("create closeableHttpAsyncClient");
		}
		closeableHttpAsyncClient.start();
		return closeableHttpAsyncClient;
	}

	/**
	 * 获取HttpAsyncClient
	 * 
	 * @return
	 * @throws Exception
	 */
	protected CloseableHttpAsyncClient getHttpAsyncClient() throws Exception {

		CloseableHttpAsyncClient httpClient = HttpAsyncClients.custom()
				.setConnectionManager(getCM(getSSLIOS("E:\\ssl.cer", "123456", false), 10)).build();
		return httpClient;
	}

	/**
	 * http 连接池
	 * 
	 * @param schemeIOSessionStrategy
	 *            ssl 连接 证书验证
	 * @param maxTotal
	 *            最大线程数
	 * @return
	 * @throws Exception
	 */
	protected PoolingNHttpClientConnectionManager getCM(SchemeIOSessionStrategy schemeIOSessionStrategy, int maxTotal)
			throws Exception {
		Registry<SchemeIOSessionStrategy> sessionStrategyRegistry = RegistryBuilder.<SchemeIOSessionStrategy> create()
				.register("http", NoopIOSessionStrategy.INSTANCE).register("https", schemeIOSessionStrategy).build();
		ConnectingIOReactor ioReactor = new DefaultConnectingIOReactor();
		PoolingNHttpClientConnectionManager cm = new PoolingNHttpClientConnectionManager(ioReactor,
				sessionStrategyRegistry);
		cm.setMaxTotal(maxTotal);
		cm.setDefaultMaxPerRoute(maxTotal);
		return cm;
	}

	/**
	 * https 请求
	 * 
	 * @param filepath
	 * @param pwd
	 * @param isTrusted
	 * @return
	 * @throws Exception
	 */
	protected SSLIOSessionStrategy getSSLIOS(String filepath, String pwd, boolean isTrusted) throws Exception {

		KeyStore trustStore = null;
		TrustStrategy trustStrategy;
		SSLContext sslcontext;
		if (isTrusted) {
			trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
			FileInputStream instream = new FileInputStream(new File(filepath));
			try {
				trustStore.load(instream, pwd.toCharArray());
			} finally {
				instream.close();
			}
			trustStrategy = new TrustSelfSignedStrategy();
		} else {
			trustStrategy = new TrustStrategy() {

				public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					return true;
				}
			};
		}
		// Trust own CA and all self-signed certs
		sslcontext = SSLContexts.custom().loadTrustMaterial(trustStore, trustStrategy).build();
		// Allow TLSv1 protocol only
		SSLIOSessionStrategy sslSessionStrategy = new SSLIOSessionStrategy(sslcontext, new String[] { "TLSv1" }, null,
				SSLIOSessionStrategy.getDefaultHostnameVerifier());
		return sslSessionStrategy;
	}

	/**
	 * 将map 转换为 BasicNameValuePair list
	 * 
	 * @param map
	 * @return
	 */
	private static List<NameValuePair> mapToArrNVP(Map<String, String> map) {
		ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
		if (map != null) {
			// 反射 数据
			Set<Map.Entry<String, String>> set = map.entrySet();
			for (Iterator<Map.Entry<String, String>> it = set.iterator(); it.hasNext();) {
				Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();

				pairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
		}
		return pairs;
	}

	public static class IdleConnectionEvictor extends Thread {

		private final NHttpClientConnectionManager connMgr;

		private volatile boolean shutdown;

		public IdleConnectionEvictor(NHttpClientConnectionManager connMgr) {
			super();
			this.connMgr = connMgr;
		}

		@Override
		public void run() {
			try {
				while (!shutdown) {
					synchronized (this) {
						wait(5000);
						// Close expired connections
						connMgr.closeExpiredConnections();
						// Optionally, close connections
						// that have been idle longer than 5 sec
						connMgr.closeIdleConnections(5, TimeUnit.SECONDS);
					}
				}
			} catch (InterruptedException ex) {
				// terminate
			}
		}

		public void shutdown() {
			shutdown = true;
			synchronized (this) {
				notifyAll();
			}
		}

	}

}