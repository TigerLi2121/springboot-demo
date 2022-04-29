package com.mm.service;

import com.mm.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author lwl
 */
@Service
@Slf4j
public class UserService {

    @Cacheable(value = {"user"}, key = "#name", condition = "#name != null")
    public Optional<UserEntity> get(String name) {
        log.info("get:{}", name);
        if (name == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(new UserEntity(name));
    }

    @Cacheable(value = {"user_name"}, key = "#name", condition = "#name != null")
    public String getName(String name) {
        log.info("get:{}", name);
        if (name == null) {
            return null;
        }
        return name;
    }

}
