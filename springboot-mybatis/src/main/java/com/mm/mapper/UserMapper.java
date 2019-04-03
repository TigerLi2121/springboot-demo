package com.mm.mapper;

import com.mm.entity.User;

import java.util.List;

public interface UserMapper {

    List<User> getAll();

    User get(Long id);

    void insert(User user);

    void update(User user);

    void delete(Long id);

}
