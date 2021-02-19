package com.mm.api.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户
 *
 * @author shmily
 */
@Accessors(chain = true)
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(required = true, value = "用户ID", name = "id", example = "1")
    private Long id;

    @ApiModelProperty(value = "用户帐号", name = "username", example = "666")
    @NotBlank(message = "username is null")
    private String username;

    @ApiModelProperty(value = "用户密码", name = "password", example = "888888")
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
