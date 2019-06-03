package com.mm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mm.dao.UserDao;
import com.mm.entity.User;
import com.mm.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 用户
 *
 * @author lwl
 * @date 2019/6/3
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {
}
