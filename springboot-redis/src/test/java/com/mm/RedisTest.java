package com.mm;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mm.utils.RedisUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

/**
 * @author: shmily
 * @date: 2018/2/13 17:32
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
    @Autowired
    private RedisUtils redisUtils;

    @Test
    public void stringTest() {
        redisUtils.set("aaa", "bbb");
        System.out.println(redisUtils.get("ccc"));
        Assert.assertEquals("bbb", redisUtils.get("aaa"));
    }

    @Test
    public void beanTest() throws Exception {
        ObjectMapper om = new ObjectMapper();
        HashMap<String, String> param = new HashMap<>();
        param.put("id", "123");
        param.put("age", "18");
        param.put("name", "哈哈");
        String json =  om.writeValueAsString(param);
        redisUtils.set("hehe", json);
        redisUtils.set("haha", json, 1);  //配置过期时间
        Thread.sleep(1000);
        //redisTemplate.delete("hehe");
        System.out.println(redisUtils.get("hehe"));
    }
}
