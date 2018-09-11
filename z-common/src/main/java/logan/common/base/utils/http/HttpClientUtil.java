package logan.common.base.utils.http;

import org.apache.http.*;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.message.BasicHeader;
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
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 *
 * 使用代理可以在你发起 Http请求之前设置一下属性 ，建议初始化工具时使用 配置
 * @see logan.common.base.utils.http.HttpClientUtil.Config
 * @see /System.setProperty("http.proxyHost", "www.proxy.com");
 * @see /System.setProperty("http.proxyPort", "8080");
 * @see /http.proxyUserName=username
 * @see /http.proxyPassword=password
 *
 * @author logan 2015-11-5
 * @qq 425018553
 */
public class HttpClientUtil {
	private static Map<String, CloseableHttpAsyncClient> closeableHttpAsyncClientMap = new ConcurrentHashMap<>();
	private static Map<String, IdleConnectionEvictor> connEvictorMap = new ConcurrentHashMap<>();

	/**
	 * get请求 callfutrue==null?同步：异步
	 * 
	 * @param url
	 *            请求地址 可带参数
	 * @param parameters
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
	public static HttpResponse httpGet(String url, Map<String, String> parameters, Map<String, List<String>> headers,
			Charset charset, FutureCallback<HttpResponse> callfutrue) throws Exception {
		URIBuilder uRIBuilder = new URIBuilder(url).setCharset(charset).addParameters(mapToArrNVP(parameters));
		HttpResponse result = null;
		URI uri = uRIBuilder.build();
		HttpGet httpGet = new HttpGet(uri);

		if (null != headers)
			httpGet.setHeaders(mapToArrHD(headers));

		Future<HttpResponse> future = HttpClientUtil.instance(url).execute(httpGet, callfutrue);
		if (null == callfutrue) {
			result = future.get();
			Header ceheader = result.getEntity().getContentEncoding();
			if (ceheader != null) {
				for (HeaderElement element : ceheader.getElements()) {
					if (element.getName().equalsIgnoreCase("gzip")) {
						result.setEntity(new GzipDecompressingEntity(result.getEntity()));
					}
				}
			}
		}
		httpGet.releaseConnection();
		return result;

		// https://blog.csdn.net/angjunqiang/article/details/55259170
	}
	/**
	 * post callfutrue==null?同步：异步
	 * 
	 * @param url
	 *            请求地址 可带参数
	 * @param inputStream
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
	public static HttpResponse httpPostInputStream(String url, InputStream inputStream,long contentLength,ContentType contentType, Map<String, List<String>> headers,
			Charset charset, FutureCallback<HttpResponse> callfutrue) throws Exception {
			HttpResponse result = null;

		HttpPost httpPost = new HttpPost(url);
		if (null != headers)
			httpPost.setHeaders(mapToArrHD(headers));
		
		InputStreamEntity reqEntity = new InputStreamEntity(inputStream, contentLength,
				contentType);
//		StringEntity reqEntity=new StringEntity("123");
		httpPost.setEntity(reqEntity);
		Future<HttpResponse> future = HttpClientUtil.instance(url).execute(httpPost, callfutrue);
		if (null == callfutrue) {
			result = future.get();
			Header ceheader = result.getEntity().getContentEncoding();
			if (ceheader != null) {
				for (HeaderElement element : ceheader.getElements()) {
					if (element.getName().equalsIgnoreCase("gzip")) {
						result.setEntity(new GzipDecompressingEntity(result.getEntity()));
					}
				}
			}
		}
		httpPost.releaseConnection();
		return result;
	}
	/**
	 * post callfutrue==null?同步：异步
	 * 
	 * @param url
	 *            请求地址 可带参数
	 * @param parameters
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
	public static HttpResponse httpPost(String url, Map<String, Object> parameters, Map<String, List<String>> headers,
			Charset charset, FutureCallback<HttpResponse> callfutrue) throws Exception {
		
		HttpPost httpPost = new HttpPost(url);
		if (null != headers)
			httpPost.setHeaders(mapToArrHD(headers));
		HttpResponse result = null;
//		UrlEncodedFormEntity p_entity = new UrlEncodedFormEntity(mapToArrNVP(parameters), charset);
		MyMultipartEntityBuilder multipartEntityBuilder = MyMultipartEntityBuilder.create().setCharset(charset);
		if (parameters != null) {
			Set<Map.Entry<String, Object>> set = parameters.entrySet();
			for (Iterator<Map.Entry<String, Object>> it = set.iterator(); it.hasNext();) {
				Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it.next();
				ContentBody contentBody;
				if(null==entry.getValue())
					continue;
				if (entry.getValue() instanceof File) {
					contentBody = new FileBody((File) entry.getValue());// 文件
				}else {
					contentBody = new StringBody((String) entry.getValue());
				}
				multipartEntityBuilder.addPart(entry.getKey(), contentBody);
			}
		}
		HttpEntity reqEntity = multipartEntityBuilder.build();
		httpPost.setEntity(reqEntity);
		Future<HttpResponse> future = HttpClientUtil.instance(url).execute(httpPost, callfutrue);
		if (null == callfutrue) {
			result = future.get();
			Header ceheader = result.getEntity().getContentEncoding();
			if (ceheader != null) {
				for (HeaderElement element : ceheader.getElements()) {
					if (element.getName().equalsIgnoreCase("gzip")) {
						result.setEntity(new GzipDecompressingEntity(result.getEntity()));
					}
				}
			}
		}
		httpPost.releaseConnection();
		return result;
	}

	/**
	 * post请求 callfutrue==null?同步：异步
	 * 
	 * @param url
	 *            请求地址 可带参数
	 * @param data
	 *            字符流形式
	 * @param headers
	 *            请求头
	 * @param charset
	 *            编码
	 * @param callfutrue
	 *            回调函数
	 * @return 当回调函数 null 时 返回 否则返回 null
	 * @throws Exception
	 */
	public static HttpResponse httpPostJson(String url, String data, Map<String, List<String>> headers, Charset charset,
			FutureCallback<HttpResponse> callfutrue) throws Exception {
		HttpPost httpPost = new HttpPost(url);
		if (null != headers)
			httpPost.setHeaders(mapToArrHD(headers));
		else {
			Map<String, List<String>> map = new HashMap<String, List<String>>();
			List<String> json = new ArrayList<>();
			json.add("application/json");
			map.put("Content-Type", json);
			httpPost.setHeaders(mapToArrHD(map));
		}
		HttpResponse result = null;
		StringEntity p_entity = new StringEntity(data, charset);
		httpPost.setEntity(p_entity);
		Future<HttpResponse> future = HttpClientUtil.instance(url).execute(httpPost, callfutrue);
		if (null == callfutrue) {
			result = future.get();
			Header ceheader = result.getEntity().getContentEncoding();
			if (ceheader != null) {
				for (HeaderElement element : ceheader.getElements()) {
					if (element.getName().equalsIgnoreCase("gzip")) {
						result.setEntity(new GzipDecompressingEntity(result.getEntity()));
					}
				}
			}
		}
		httpPost.releaseConnection();
		return result;
	}

	public static CloseableHttpAsyncClient instance(String url) throws Exception {
		URL uri = new URL(url);
		String urlKey = uri.getProtocol() + "//" + uri.getAuthority();

		CloseableHttpAsyncClient closeableHttpAsyncClient = closeableHttpAsyncClientMap.get(urlKey);
		if (null == closeableHttpAsyncClient) {
			closeableHttpAsyncClient = new HttpClientUtil().createHttpAsyncClient(urlKey, new Config());
			closeableHttpAsyncClient.start();
		}
		closeableHttpAsyncClientMap.put(urlKey, closeableHttpAsyncClient);
		return closeableHttpAsyncClient;
	}

	/**
	 * 获取HttpAsyncClient
	 * 
	 * @return
	 * @throws Exception
	 */
	protected CloseableHttpAsyncClient createHttpAsyncClient(String urlKey, Config config) throws Exception {
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(config.getConnectTimeout())
				.setSocketTimeout(config.getSocketTimeout()).build();
		PoolingNHttpClientConnectionManager cm = null;
		ConnectingIOReactor ioReactor = new DefaultConnectingIOReactor();
		if (null != config.getSslFilePath()) {
			SchemeIOSessionStrategy schemeIOSessionStrategy = getSSLIOS(config.getSslFilePath(), config.getSslPwd());
			Registry<SchemeIOSessionStrategy> sessionStrategyRegistry = RegistryBuilder
					.<SchemeIOSessionStrategy> create().register("http", NoopIOSessionStrategy.INSTANCE)
					.register("https", schemeIOSessionStrategy).build();
			cm = new PoolingNHttpClientConnectionManager(ioReactor, sessionStrategyRegistry);
		} else {
			cm = new PoolingNHttpClientConnectionManager(ioReactor);
		}
		cm.setMaxTotal(config.getMaxToal());
		cm.setDefaultMaxPerRoute(config.getMaxToal());

		CloseableHttpAsyncClient httpClient;
		if (null != config.getProxyHost()) {
			HttpHost proxy = new HttpHost(config.getProxyHost(), config.getProxyPort());
			UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(config.getProxyUserName(),
					config.getProxyPwd());
			CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
			credentialsProvider.setCredentials(AuthScope.ANY, credentials);
			DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
			httpClient = HttpAsyncClients.custom().setRoutePlanner(routePlanner).setDefaultRequestConfig(requestConfig)
					.setConnectionManager(cm).setDefaultCredentialsProvider(credentialsProvider).build();
		} else {
			httpClient = HttpAsyncClients.custom().setDefaultRequestConfig(requestConfig).setConnectionManager(cm)
					.build();

		}
		// 关闭异常连接
		IdleConnectionEvictor connEvictor = new IdleConnectionEvictor(cm);
		connEvictor.start();
		connEvictorMap.put(urlKey, connEvictor);
		return httpClient;
	}

	public static void close() throws IOException {
		for (String key : closeableHttpAsyncClientMap.keySet()) {
			IdleConnectionEvictor idleConnectionEvictor = connEvictorMap.remove(key);
			idleConnectionEvictor.shutdown();
			idleConnectionEvictor = null;
			CloseableHttpAsyncClient closeableHttpAsyncClient = closeableHttpAsyncClientMap.remove(key);
			closeableHttpAsyncClient.close();
			closeableHttpAsyncClient = null;

		}
	}

	/**
	 * https 请求
	 * 
	 * @param filepath
	 * @param pwd
	 * @return
	 * @throws Exception
	 */
	protected SSLIOSessionStrategy getSSLIOS(String filepath, String pwd) throws Exception {

		KeyStore trustStore = null;
		TrustStrategy trustStrategy;
		SSLContext sslcontext;
		FileInputStream instream = new FileInputStream(new File(filepath));
		if(filepath.indexOf("cer")>-1) {
			// 证书工厂
//			input = new ByteArrayInputStream(CER_12306.getBytes("UTF-8")); //证书内容存放在String中
			CertificateFactory cerFactory = CertificateFactory.getInstance("X.509");
			Certificate cer = cerFactory.generateCertificate(instream);
			// 密钥库
			trustStore = KeyStore.getInstance("PKCS12");
			trustStore.load(null, null);
			trustStore.setCertificateEntry("trust", cer);// 加载证书到密钥库中
		}
		trustStore = KeyStore.getInstance(KeyStore.getDefaultType());

		try {
			trustStore.load(instream, pwd.toCharArray());
		} finally {
			instream.close();
		}
		trustStrategy = new TrustSelfSignedStrategy();
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
	private static Header[] mapToArrHD(Map<String, List<String>> map) {
		List<Header> HeaderList = new ArrayList<Header>();
		if (map != null) {
			// 反射 数据
			map.remove("Content-Length");
			map.remove("content-Length");
			Set<Map.Entry<String, List<String>>> set = map.entrySet();
			for (Iterator<Map.Entry<String, List<String>>> it = set.iterator(); it.hasNext();) {
				Map.Entry<String, List<String>> entry = (Map.Entry<String, List<String>>) it.next();
				for (String val : entry.getValue())
					HeaderList.add(new BasicHeader(entry.getKey(), val));
			}
		}
		Header[] headers = new Header[HeaderList.size()];
		return HeaderList.toArray(headers);
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

	public static class Config {
		private String proxyHost;
		private int proxyPort;
		private String proxyUserName="";
		private String proxyPwd="";
		private String sslFilePath;
		private String sslPwd;
		private int maxToal = 20;
		private int socketTimeout = 120000;// 设置等待数据超时时间 根据业务调整
		private int connectTimeout = 5000;// 连接超时

		public int getSocketTimeout() {
			return socketTimeout;
		}

		public void setSocketTimeout(int socketTimeout) {
			this.socketTimeout = socketTimeout;
		}

		public int getConnectTimeout() {
			return connectTimeout;
		}

		public void setConnectTimeout(int connectTimeout) {
			this.connectTimeout = connectTimeout;
		}

		public String getProxyUserName() {
			return proxyUserName;
		}

		public void setProxyUserName(String proxyUserName) {
			this.proxyUserName = proxyUserName;
		}

		public String getProxyPwd() {
			return proxyPwd;
		}

		public void setProxyPwd(String proxyPwd) {
			this.proxyPwd = proxyPwd;
		}

		public String getProxyHost() {
			return proxyHost;
		}

		public void setProxyHost(String proxyHost) {
			this.proxyHost = proxyHost;
		}

		public int getProxyPort() {
			return proxyPort;
		}

		public void setProxyPort(int proxyPort) {
			this.proxyPort = proxyPort;
		}

		public String getSslFilePath() {
			return sslFilePath;
		}

		public void setSslFilePath(String sslFilePath) {
			this.sslFilePath = sslFilePath;
		}

		public String getSslPwd() {
			return sslPwd;
		}

		public void setSslPwd(String sslPwd) {
			this.sslPwd = sslPwd;
		}

		// http 线程池
		public int getMaxToal() {
			return maxToal;
		}

		public void setMaxToal(int maxToal) {
			this.maxToal = maxToal;
		}

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
						wait(50000);
						// Close expired connections
						connMgr.closeExpiredConnections();
						// Optionally, close connections
						// that have been idle longer than
						connMgr.closeIdleConnections(10, TimeUnit.SECONDS);
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