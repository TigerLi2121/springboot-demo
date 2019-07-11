package com.mm.dao;

import com.mm.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * 用户
 *
 * @author lwl
 * @date 2019/6/3
 */
@Repository
public interface UserDao extends CrudRepository<User, Long> {
}
