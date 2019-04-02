package com.mm;

import com.mm.entity.UserEntity;
import com.mm.enums.UserSexEnum;
import com.mm.mapper.UserMapper;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/8/13.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {
    @Resource
    private UserMapper userMapper;

    @org.junit.Test
    public void saveTest() {
        UserEntity user = new UserEntity();
        user.setUserName("hehehe");
        user.setPassWord("6666666");
        user.setNickName("哈哈");
        user.setUserSex(UserSexEnum.MAN);
        user.setGmtCreate(new Date());
        user.setGmtModified(new Date());
        userMapper.insert(user);
    }

    @org.junit.Test
    public void queryAllTest() {
        List<UserEntity> users = userMapper.getAll();
        System.out.println(users.toString());
    }

    @org.junit.Test
    public void updateTest() {
        UserEntity user = new UserEntity();
        user.setId(5L);
        user.setUserName("哈哈");
        user.setPassWord("888888");
        user.setNickName("hehe");
        user.setUserSex(UserSexEnum.WOMAN);
        user.setGmtModified(new Date());
        userMapper.update(user);
    }
}
