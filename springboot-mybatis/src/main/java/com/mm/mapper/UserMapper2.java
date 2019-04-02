package com.mm.mapper;

import com.mm.entity.UserEntity;
import com.mm.enums.UserSexEnum;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/7/8.
 *  @Select 是查询类的注解，所有的查询均使用这个
 *  @Result 修饰返回的结果集，关联实体类属性和数据库字段一一对应，如果实体类属性和数据库属性名保持一致，就不需要这个属性来修饰。
 *  @Insert 插入数据库使用，直接传入实体类会自动解析属性到对应的值
 *  @Update 负责修改，也可以直接传入对象
 *  @delete 负责删除
 */
public interface UserMapper2 {

    @Select("SELECT * FROM users")
    @Results({
            @Result(property = "userName",  column = "user_name"),
            @Result(property = "passWord",  column = "pass_word"),
            @Result(property = "nickName",  column = "nick_name"),
            @Result(property = "userSex", column = "user_sex", javaType = UserSexEnum.class),
            @Result(property = "gmtCreate", column = "gmt_create"),
            @Result(property = "gmtModified", column="gmt_modified")
    })
    List<UserEntity> getAll();

    @Select("SELECT * FROM users WHERE id = #{id}")
    @Results({
            @Result(property = "userName",  column = "user_name"),
            @Result(property = "passWord",  column = "pass_word"),
            @Result(property = "nickName", column = "nick_name"),
            @Result(property = "userSex",  column = "user_sex", javaType = UserSexEnum.class),
            @Result(property = "gmtCreate", column = "gmt_create"),
            @Result(property = "gmtModified", column="gmt_modified")
    })
    UserEntity getOne(Long id);

    @Insert("INSERT INTO users(user_name,pass_word,nick_name,user_sex,gmt_create,gmt_modified) VALUES(#{userName}, #{passWord}, #{nickName}, #{userSex}, #{gmtCreate}, #{gmtModified})")
    void insert(UserEntity user);

    @Update("UPDATE users SET user_name=#{userName},nick_name=#{nickName},gmt_modified=#{gmtModified} WHERE id =#{id}")
    void update(UserEntity user);

    @Delete("DELETE FROM users WHERE id =#{id}")
    void delete(Long id);

}
