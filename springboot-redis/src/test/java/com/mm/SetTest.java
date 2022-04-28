package com.mm;

import com.mm.util.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * set 测试
 *
 * @author shmily
 * @date 2019/4/16
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SetTest {

    public final String SINGLE_DEMAND_FLAG = "single-demand-flag";


    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void save() {
        System.out.println(RedisUtil.set(SINGLE_DEMAND_FLAG, "11"));
        System.out.println(RedisUtil.set(SINGLE_DEMAND_FLAG, "22"));
        System.out.println(redisTemplate.opsForSet().randomMember(SINGLE_DEMAND_FLAG));
    }
}
