package com.mm;

import com.mm.entity.User;
import com.mm.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * test
 *
 * @author lwl
 */
@SpringBootTest(classes = MybatisFlexApp.class)
public class MybatisFlexTest {

    @Autowired
    private UserService userService;

    @Test
    public void saveBatch() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            User user = new User();
            user.setUsername("username" + i);
            user.setPassword("password" + i);
            user.setNickName("nickName" + i);
            user.setGender(i % 2 == 0 ? User.Gender.MALE : User.Gender.FEMALE);
            users.add(user);
        }
        userService.saveBatch(users);
    }
}
