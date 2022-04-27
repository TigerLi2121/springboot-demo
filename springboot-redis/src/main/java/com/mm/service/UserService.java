package com.mm.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author lwl
 */
@Service
@Slf4j
public class UserService {

    @Cacheable(value = {"user_id"}, key = "#id", condition = "#id != null")
    public String get(String id) {
        log.info("get:{}", id);
        if (id == null) {
            return null;
        }
        return "id:" + id;
    }

}
