package com.mm;

import com.mm.entity.UserEntity;
import com.mm.service.UserService;
import com.mm.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class UserTest {

    @Autowired
    private UserService userService;

    @Test
    public void get() {
        System.out.println(userService.get("mm"));
        System.out.println(userService.get("mm"));
        System.out.println(RedisUtil.get("user::mm"));
    }

    @Test
    public void getName() {
        System.out.println(userService.getName("ll"));
        System.out.println(userService.getName("ll"));
        System.out.println(RedisUtil.get("user_name::ll"));
        System.out.println(RedisUtil.get("user_name::ll").toString().equals("ll"));
    }

    @Test
    public void str() {
//        RedisUtil.set("aa", "bb");
//        System.out.println(RedisUtil.get("aa").equals("bb"));
        System.out.println(RedisUtil.get("cc"));
    }

    @Test
    public void user() {
        RedisUtil.set("user::aa", new UserEntity("cc"));
        System.out.println(RedisUtil.get("user::aa"));
        System.out.println(userService.get("aa").isPresent());
    }

    @Test
    public void map() {
        Map<String, Object> map = new HashMap<>();
        map.put("aa", 123);
        RedisUtil.set("map", map);
        Map<String, Object> map2 = (Map<String, Object>) RedisUtil.get("map");
        System.out.println(map2);
    }

}
