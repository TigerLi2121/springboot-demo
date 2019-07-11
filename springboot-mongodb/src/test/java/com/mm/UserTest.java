package com.mm;

import com.mm.dao.UserDao;
import com.mm.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

/**
 * @author lwl
 * @date 2019/7/11
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    @Autowired
    private UserDao dao;

    @Test
    public void save() {
        dao.save(User.builder().id(1L).username("mm").password("ll").nickName("hh")
                .gender(User.Gender.FEMALE).createDate(LocalDateTime.now()).updateDate(LocalDateTime.now()).build());
    }
}
