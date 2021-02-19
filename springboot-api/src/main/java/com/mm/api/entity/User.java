package com.mm.api.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户
 *
 * @author shmily
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(required = true, value = "用户ID", name = "id", example = "1")
    private Long id;

    @ApiModelProperty(value = "用户帐号", name = "username", example = "abc123")
    @NotBlank(message = "username is null")
    private String username;

    @ApiModelProperty(value = "用户密码", name = "password", example = "mm123")
    @NotBlank(message = "password is null")
    private String password;

    @ApiModelProperty(value = "用户昵称", name = "nickName", example = "娃娃")
    private String nickName;

    @ApiModelProperty(value = "用户性别(1=男,2=女)", name = "gender", example = "1")
    private Integer gender;

    @ApiModelProperty(value = "用户年龄", name = "age", example = "22")
    private Integer age;

    @ApiModelProperty(value = "创建时间", name = "createTime", example = "2018-08-08 08:08:08")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间", name = "updateTime", example = "2018-08-08 08:08:08")
    private LocalDateTime updateTime;

}
