package com.mm.api.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户
 *
 * @author shmily
 */
@Data
@Validated
@Schema(description = "用户")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @Schema(description = "用户名")
    @NotBlank(message = "username is null")
    private String username;

    @Schema(description = "密码")
    @NotBlank(message = "password is null")
    private String password;

    @Schema(name = "nick_name", description = "昵称")
    private String nickName;

    @Schema(description = "性别")
    private Integer gender;

    @Schema(description = "年龄")
    private Integer age;

    @Schema(name = "create_time", description = "创建时间", example = "2022-08-08 08:08:08")
    private LocalDateTime createTime;

    @Schema(name = "update_time", description = "修改时间", example = "2022-08-08 08:08:08")
    private LocalDateTime updateTime;
}
