package com.mm.api.service;

import com.mm.api.entity.User;
import com.mm.api.exception.GException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserService {

    private final static ConcurrentHashMap<Long, User> users = new ConcurrentHashMap<>();
    private final Integer age = 18;

    public void add(User user) {
        if (age > user.getAge()) {
            throw new GException("年龄不能低于18");
        }
        users.put(user.getId(), user);
    }

    public List<User> list() {
        return new ArrayList<>(users.values());
    }

    public User get(Long id) {
        return users.get(id);
    }

    public void update(User user) {
        User u = users.get(user.getId())
                .setPassword(user.getPassword())
                .setNickName(user.getNickName())
                .setGender(user.getGender())
                .setAge(user.getAge())
                .setUpdateTime(LocalDateTime.now());
        users.put(user.getId(), u);
    }

    public void del(Long id) {
        users.remove(id);
    }
}
