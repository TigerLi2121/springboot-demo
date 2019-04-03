package com.mm;

import com.mm.entity.User;
import com.mm.mapper.UserMapper2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 注解
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapper2Test {
    @Resource
    private UserMapper2 userMapper2;

    @Test
    public void save() {
        User user = new User();
        user.setUsername("hehehe");
        user.setPassword("6666666");
        user.setNickName("呵呵呵呵");
        user.setGender(User.Gender.MALE);
        user.setGmtCreate(new Date());
        user.setGmtModified(new Date());
        userMapper2.insert(user);
    }

    @Test
    public void update() {
        User user = new User();
        user.setId(1L);
        user.setUsername("hehe");
        user.setPassword("666666");
        user.setNickName("呵呵");
        user.setGender(User.Gender.MALE);
        user.setGmtModified(new Date());
        userMapper2.update(user);
    }

    @Test
    public void delete() {
        userMapper2.delete(3L);
    }

    @Test
    public void get() {
        User user = userMapper2.get(1L);
        System.out.println(user);
    }

    @Test
    public void getAll() {
        List<User> users = userMapper2.getAll();
        System.out.println(users.toString());
    }
}
