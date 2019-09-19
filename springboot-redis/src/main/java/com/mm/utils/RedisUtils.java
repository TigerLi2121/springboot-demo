package com.mm.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * redis
 *
 * @author shmily
 * @date 2019/8/30
 */
@Component
public class RedisUtils {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    /**  默认过期时长，单位：秒 */
    public final static long DEFAULT_EXPIRE = 60 * 60 * 24;
    /**  不设置过期时长 */
    public final static long NOT_EXPIRE = -1;

    public void set(String key, String value, long expire){
        redisTemplate.opsForValue().set(key, value);
        if(expire != NOT_EXPIRE){
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
    }

    public void set(String key, String value){
        set(key, value, DEFAULT_EXPIRE);
    }

    public String get(String key, long expire) {
        String value = redisTemplate.opsForValue().get(key);
        if(expire != NOT_EXPIRE){
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
        return value;
    }

    public String get(String key) {
        return get(key, NOT_EXPIRE);
    }


}
