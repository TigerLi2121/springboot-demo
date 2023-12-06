package com.mm.mapper;

import com.mm.entity.User;
import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户
 *
 * @author tigerli
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
