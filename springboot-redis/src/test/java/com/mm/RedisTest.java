package com.mm;

import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mm.util.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author: shmily
 * @date: 2018/2/13 17:32
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Test
    public void stringTest() {
        Map<String, String> p = new TreeMap<>();
        p.put("a", "b");
        RedisUtil.set("map", JSONUtil.toJsonStr(p));
        System.out.println(RedisUtil.get("map"));
//        RedisUtil.set("aaa", "bbb");
//        System.out.println(RedisUtil.get("ccc"));
//        Assert.assertEquals("bbb", RedisUtil.get("aaa"));
    }

    @Test
    public void beanTest() throws Exception {
        ObjectMapper om = new ObjectMapper();
        HashMap<String, String> param = new HashMap<>();
        param.put("id", "123");
        param.put("age", "18");
        param.put("name", "哈哈");
        String json = om.writeValueAsString(param);
        RedisUtil.set("hehe", json);
        RedisUtil.set("haha", json, 1L);  //配置过期时间
        Thread.sleep(1000);
        //redisTemplate.delete("hehe");
        System.out.println(RedisUtil.get("hehe"));
    }
}
