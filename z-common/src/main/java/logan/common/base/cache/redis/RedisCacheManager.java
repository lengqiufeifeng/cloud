package logan.common.base.cache.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Desc：
 * @Title：RedisManager
 * @address:
 * @author:
 * @version: 1.0
 * @date: 2016年11月3日 下午8:07:50
 */
public class RedisCacheManager {
    protected final static Logger logger = LoggerFactory.getLogger(RedisCacheManager.class);
    // Redis服务器IP
    private static String ADDR = "127.0.0.1";

    // Redis的端口号
    private static int PORT = 6379;

    // 访问密码
    private static String AUTH = "admin";

    // 可用连接实例的最大数目，默认值为8；
    // 如果赋值为-1，则表示不限制；如果pool已经分配了MAX_TOTAL个jedis实例，则此时pool的状态为exhausted(耗尽)。
    private static int MAX_TOTAL = 10;

    // 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
    private static int MAX_IDLE = 20;

    // 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
    private static int MAX_WAIT = 1000;

    private static int TIMEOUT = 1000;

    // 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
    private static boolean TEST_ON_BORROW = true;

    private static int DATABASE_NUM = 16;
//	private static int DATABASE_INDEX = 0;

    private static JedisPool jedisPool = null;
    private static ConcurrentHashMap<String, RedisCacheWrapper> cacheWrapperMap = new ConcurrentHashMap<String, RedisCacheWrapper>();

    /**
     * @param addr
     * @param port
     * @param auth
     * @Desc:
     * @Author: qiufeng 2016年11月25日 上午11:14:52
     */
    public static void setConfig(String addr, int port, String auth) {
        ADDR = addr;
        PORT = port;
        AUTH = auth;
    }

    /**
     * @param addr
     * @param port
     * @param auth
     * @param max_total
     * @param max_idle
     * @param max_wait
     * @param timeout
     * @param test_on_borrow
     * @Desc:
     * @Author: qiufeng 2016年11月24日 下午3:34:12
     */
    public static void setConfig(String addr, int port, String auth, int max_total, int max_idle, int max_wait,
                                 int timeout, boolean test_on_borrow, int database_num) {
        ADDR = addr;
        PORT = port;
        AUTH = auth;
        MAX_TOTAL = max_total == 0 ? MAX_TOTAL : max_total;
        MAX_IDLE = max_idle == 0 ? MAX_IDLE : max_idle;
        MAX_WAIT = max_wait == 0 ? MAX_WAIT : max_wait;
        TIMEOUT = timeout == 0 ? TIMEOUT : timeout;
        TEST_ON_BORROW = test_on_borrow;
        DATABASE_NUM = database_num == 0 ? DATABASE_NUM : database_num;
    }

    public static RedisCacheWrapper getRedisCacheWrapperByName(String cacheName) {
        if (cacheWrapperMap.containsKey(cacheName)) {
            logger.debug("取到缓存对象，" + cacheName);
            return cacheWrapperMap.get(cacheName);
        } else {
            logger.error("没有缓存对象，" + cacheName);
            return null;
        }
    }

    /**
     * 初始化Redis连接池
     */
    public static void initConfig() {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(MAX_TOTAL);
            config.setMaxIdle(MAX_IDLE);
            config.setMaxWaitMillis(MAX_WAIT);
            config.setTestOnBorrow(TEST_ON_BORROW);
            jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT, AUTH);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param cacheName 缓存名称
     * @param index     使用库索引 从0开始
     * @throws Exception
     * @Desc:
     * @Author: qiufeng 2016年12月6日 下午2:30:52
     */
    public static void createCache(String cacheName, int index) throws Exception {
        if (!cacheWrapperMap.containsKey(cacheName)) {
            if (index == (DATABASE_NUM - 1)) {
                throw new Exception("Redis数据库最大索引：" + DATABASE_NUM);
            }
            cacheWrapperMap.put(cacheName, new RedisCacheWrapper(cacheName, index));
            logger.info("创建Redis缓存：" + cacheName + "_索引：" + index);
        } else {
            logger.info(cacheName + " cacheObj has existed." );
        }
    }

    /**
     * 获取Jedis实例,不推荐直接使用 推荐使用 先创建不同{@link RedisCacheWrapper}并使用 可以按照不同模块使用不同库
     *
     * @return
     */
    public synchronized static Jedis getJedis() {
        try {
            if (jedisPool != null) {
                Jedis resource = jedisPool.getResource();
                return resource;
            } else {
                initConfig();
                if (jedisPool != null) {
                    Jedis resource = jedisPool.getResource();
                    return resource;
                } else {
                    return null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 释放jedis连接
     *
     * @param jedis
     */
    public static void close() {
        if (null != jedisPool) {
            jedisPool.close();
        }
    }

    public static void flushAll() {
        if (null != jedisPool) {
            getJedis().flushAll();
        }
    }

    public static void main(String arg[]) {
        RedisCacheManager.setConfig("172.19.5.28", 6379, null);
//		RedisCacheManager.initConfig();
        try {
            RedisCacheManager.createCache("one", 1);
            RedisCacheManager.createCache("two", 2);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        RedisCacheWrapper one = RedisCacheManager.getRedisCacheWrapperByName("one" );
        RedisCacheWrapper two = RedisCacheManager.getRedisCacheWrapperByName("two" );
        System.out.println("分别向不同库添加相同key、value" );
        one.setObject("one", "one" );
        two.setObject("one", "one" );
        System.out.println("分别输入不同库添加相同key、value" );
        String one_s = (String) one.getObject("one" );
        System.out.println(one_s);
        String two_s = (String) two.getObject("one" );
        System.out.println(two_s);
        System.out.println("清除one后分别输入不同库添加相同key、value" );
        one.removeAll();
        one_s = (String) one.getObject("one" );
        System.out.println(one_s);
        two_s = (String) two.getObject("one" );
        System.out.println(two_s);
        System.out.println("清除all后分别输入不同库添加相同key、value" );
        RedisCacheManager.flushAll();
        one_s = (String) one.getObject("one" );
        System.out.println(one_s);
        two_s = (String) two.getObject("one" );
        System.out.println(two_s);
    }
}