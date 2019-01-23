package logan.common.base.cache.redis;


import logan.common.base.utils.SerializeUtils;
import org.apache.commons.lang3.SerializationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.io.Serializable;

/**
 * 封装redis 用于区分不同模块缓存
 *
 * @Title：RedisCacheWrapper
 * @author: qiufeng
 * @version: 1.0
 * @date: 2016年11月25日 上午10:43:54
 */
public class RedisCacheWrapper {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    private String cacheName = null;
    private int index;

    public RedisCacheWrapper(String cacheName, int index) {
        this.cacheName = cacheName;
        this.index = index;
    }

    public String getKey(String key) {
        return this.cacheName + key;
    }

    /**
     * @return
     * @Desc:使用此方法key需要调用getkey
     * @Author: qiufeng 2016年11月25日 下午1:28:53
     */
    public Jedis getJedis() {
        Jedis cache = RedisCacheManager.getJedis();
        cache.select(index);// 使用不同库
        return cache;
    }

    public void setObject(String key, final Serializable obj) {
        setObject(key, obj, 0);
    }

    public void setObject(String key, final Serializable obj, int expire) {
        key = cacheName + key;
        Jedis cache = getJedis();
        try {
            cache.set(key.getBytes(), SerializationUtils.serialize(obj));
            if (expire != 0) {
                cache.expire(key, expire);
            }
        } catch (Exception e) {
            logger.error("Method set(String key, Object value),Put Element to the Cache Error,Key is [" + key, e);
        } finally {
            cache.close();
        }

    }

    public Object getObject(String key) {
        Jedis cache = getJedis();
        key = cacheName + key;
        Object obj = SerializationUtils.deserialize(cache.get(key.getBytes()));
        cache.close();
        return obj;
    }

    public Object removeObject(String key) {
        Jedis cache = getJedis();
        key = cacheName + key;
        Object obj = cache.get(key.getBytes());
        cache.del(key.getBytes());
        cache.close();
        return obj;
    }

    public void removeAll() {
        Jedis cache = getJedis();
        cache.flushDB();
        cache.close();
    }

}
