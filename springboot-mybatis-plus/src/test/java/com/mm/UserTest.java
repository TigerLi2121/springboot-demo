package com.mm;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mm.entity.User;
import com.mm.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author tigerli
 * @date 2019/6/3
 */
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
        userService.updateById(user);
    }

    @Test
    public void update2() {
        userService.update(User.builder().password("666").build(),
                new UpdateWrapper<User>().eq("username", "hehehe"));
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

    @Test
    public void page() {
        IPage<User> page = userService.page(new Page<>(1, 10));
        System.out.println(page.getTotal());
        page.getRecords().forEach(System.out::println);
    }
}
