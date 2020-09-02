package org.datadog.jmxfetch.util;

import redis.clients.jedis.*;

/**
 * Redis client util.
 *
 * @author jackpan
 * @version 1.0
 * @classname RedisUtil
 * @description 连接redis获取数据
 * @date 2018/11/12 14:03
 **/
public class RedisUtil {

    /**
     * Redis connect pool
     * */
    private static JedisPool REDIS_POOL;


    private static final String AUTH = "1234567";
    private static final int SELECT_DB = 0;
    private static final int MAX_ACTIVE = 1024;
    private static final int MAX_IDLE = 200;
    private static final int MAX_WAIT = 10000;
    private static final int TIMEOUT = 10000;
    private static final boolean TEST_ON_BORROW = true;

    /**
     * Redis client init.
     *
     * @param redisIp The redis IP
     * @param redisPort The redis port
     */
    public static void init(String redisIp, int redisPort) {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(MAX_ACTIVE);
        config.setMaxIdle(MAX_IDLE);
        config.setMaxWaitMillis(MAX_WAIT);
        config.setTestOnBorrow(TEST_ON_BORROW);
        REDIS_POOL =
            new JedisPool(
                config,
                redisIp,
                redisPort,
                TIMEOUT,
                AUTH,
                SELECT_DB);
    }

    /*
     * @description 获得redis 实例，所有调用该方法都必须等上个调用完成才能进行分配
     * @params []
     * @return redis.clients.jedis.Jedis
     * @author Huang Bing
     * @time 2018/11/12 14:52
     */
    public static Jedis getJedis() {
        if (REDIS_POOL == null) {
            return null;
        }
        return REDIS_POOL.getResource();
    }



}

