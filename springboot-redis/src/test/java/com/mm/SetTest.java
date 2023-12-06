package com.mm;

import com.mm.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * set 测试
 *
 * @author tigerli
 * @date 2019/4/16
 */
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
