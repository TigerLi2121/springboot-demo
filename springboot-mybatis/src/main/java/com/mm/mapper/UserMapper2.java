package com.mm.mapper;

import com.mm.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper2 {

    @Select("SELECT * FROM user")
    @Results({
            @Result(property = "username", column = "user_name"),
            @Result(property = "password", column = "pass_word"),
            @Result(property = "nickName", column = "nick_name"),
            @Result(property = "gender", column = "gender", javaType = User.Gender.class),
            @Result(property = "gmtCreate", column = "gmt_create"),
            @Result(property = "gmtModified", column = "gmt_modified")
    })
    List<User> getAll();

    @Select("SELECT * FROM user WHERE id = #{id}")
    @Results({
            @Result(property = "username", column = "user_name"),
            @Result(property = "password", column = "pass_word"),
            @Result(property = "nickName", column = "nick_name"),
            @Result(property = "gender", column = "gender", javaType = User.Gender.class),
            @Result(property = "gmtCreate", column = "gmt_create"),
            @Result(property = "gmtModified", column = "gmt_modified")
    })
    User get(Long id);

    @Insert("INSERT INTO user(user_name,pass_word,nick_name,gender,gmt_create,gmt_modified) VALUES(#{username}, #{password}, #{nickName}, #{gender}, #{gmtCreate}, #{gmtModified})")
    void insert(User user);

    @Update("UPDATE user SET user_name=#{username},nick_name=#{nickName},gmt_modified=#{gmtModified} WHERE id =#{id}")
    void update(User user);

    @Delete("DELETE FROM user WHERE id =#{id}")
    void delete(Long id);

}
