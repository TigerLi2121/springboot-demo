package com.mm.service.impl;

import com.mm.entity.User;
import com.mm.mapper.UserMapper;
import com.mm.service.UserService;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 用户
 *
 * @author tigerli
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
