package com.mm;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LockTest {

    @Autowired
    private RedissonClient redisson;

    @Test
    public void lock(){
        String key ="product:001";
        RLock lock = redisson.getLock(key);
        try {
            boolean res = lock.tryLock(10,100, TimeUnit.SECONDS);
            if (res){
                System.out.println("这里是你的业务代码");
            }else{
                System.out.println("系统繁忙");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
