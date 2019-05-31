package com.mm;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author: Shmily
 * @date: 2018/2/13 17:32
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void stringTest(){
        stringRedisTemplate.opsForValue().set("aaa", "bbb");
        Assert.assertEquals("bbb", stringRedisTemplate.opsForValue().get("aaa"));
    }

    @Test
    public void beanTest() throws Exception{
        HashMap<String, String> param = new HashMap<>();
        param.put("id", "123");
        param.put("age", "18");
        param.put("name", "哈哈");
        ValueOperations<String, HashMap<String, String>> operations=redisTemplate.opsForValue();
        operations.set("hehe", param);
        operations.set("haha", param,1, TimeUnit.SECONDS);  //配置过期时间
        Thread.sleep(1000);
        //redisTemplate.delete("hehe");
        boolean exists=redisTemplate.hasKey("haha");
        if(exists){
            System.out.println("exists is true");
        }else{
            System.out.println("exists is false");
        }
        System.out.println(operations.get("hehe"));
        Assert.assertEquals("哈哈", operations.get("hehe").get("name"));
    }
}
