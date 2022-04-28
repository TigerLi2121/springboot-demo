package com.mm;

import com.mm.service.UserService;
import com.mm.util.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    @Autowired
    private UserService userService;

    @Test
    public void get() {
        System.out.println(userService.get("mm"));
        System.out.println(userService.get("mm"));
        System.out.println(RedisUtil.get("user::mm").get());
    }

    @Test
    public void getName() {
        System.out.println(userService.getName("ll"));
        System.out.println(userService.getName("ll"));
        System.out.println(RedisUtil.get("user_name::ll").get());
        System.out.println(RedisUtil.get("user_name::ll").get().toString().equals("ll"));
    }

    @Test
    public void str() {
        RedisUtil.set("aa", "bb");
        System.out.println(RedisUtil.get("aa").get().equals("bb"));
    }

}
