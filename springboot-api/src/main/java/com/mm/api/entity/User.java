package com.mm.api.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(required = true, value = "用户ID", name = "id", example = "1")
    private Long id;

    @ApiModelProperty(value = "用户帐号", name = "username", example = "666")
    private String username;

    @ApiModelProperty(value = "用户密码", name = "password", example = "888888")
    private String password;

    @ApiModelProperty(value = "用户昵称", name = "nickName", example = "娃娃")
    private String nickName;

    @ApiModelProperty(value = "用户性别", name = "gender", example = "FEMALE")
    private Gender gender;

    public enum Gender {
        //男
        MALE,
        //女
        FEMALE
    }

    @ApiModelProperty(value = "用户年龄", name = "age", example = "22")
    private Integer age;

    @ApiModelProperty(value = "创建时间", name = "gmtCreate", example = "2018-08-08 08:08:08")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "修改时间", name = "gmtModified", example = "2018-08-08 08:08:08")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime gmtModified;

}
