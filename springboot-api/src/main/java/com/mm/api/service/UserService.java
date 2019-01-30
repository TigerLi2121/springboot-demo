package com.mm.api.service;

import com.mm.api.entity.User;
import com.mm.api.enums.REnum;
import com.mm.api.exception.ServiceException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserService {

    private final static ConcurrentHashMap<Long, User> users = new ConcurrentHashMap<>();
    private final Integer age = 18;

    public void addUser(User user) throws Exception {
        if (age > user.getAge()) {
            throw new ServiceException(REnum.UNDER_EIGHTEEN);
        }
        users.put(user.getId(), user);
    }

    public List<User> getUserList() {
        return new ArrayList<>(users.values());
    }

    public User getUser(Long id) {
        return users.get(id);
    }

    public void updateUser(Long id, User user) {
        User u = users.get(id)
                .setPassWord(user.getPassWord())
                .setNickName(user.getNickName())
                .setUserSex(user.getUserSex())
                .setAge(user.getAge())
                .setGmtModified(new Date());
        users.put(id, u);
    }

    public void deleteUser(Long id) {
        users.remove(id);
    }
}
