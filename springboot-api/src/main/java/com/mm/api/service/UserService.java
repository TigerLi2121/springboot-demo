package com.mm.api.service;

import com.mm.api.entity.User;
import com.mm.api.exception.GException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserService {

    private final static ConcurrentHashMap<Long, User> users = new ConcurrentHashMap<>();

    public void add(User user) {
        if (18 > user.getAge()) {
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
        users.put(user.getId(), user);
    }

    public void del(Long id) {
        users.remove(id);
    }
}
