package com.mm;

import com.mm.entity.User;
import com.mm.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

/**
 * @author lwl
 * @date 2019/6/3
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    @Autowired
    private UserService userService;

    @Test
    public void save() {
        User user = new User();
        user.setUsername("hehehe");
        user.setPassword("888888");
        user.setNickName("哈哈");
        user.setGender(User.Gender.MALE);
        user.setGmtCreate(new Date());
        user.setGmtModified(new Date());
        userService.save(user);
    }

    @Test
    public void update() {
        User user = new User();
        user.setId(1L);
        user.setUsername("haha");
        user.setPassword("33333");
        user.setNickName("呵呵");
        user.setGender(User.Gender.FEMALE);
        user.setGmtModified(new Date());
        userService.updateById(user);
    }

    @Test
    public void delete() {
        userService.removeById(2L);
    }

    @Test
    public void get() {
        User user = userService.getById(2L);
        System.out.println(user);
    }

    @Test
    public void getAll() {
        List<User> users = userService.list();
        users.forEach(System.out::println);
    }
}