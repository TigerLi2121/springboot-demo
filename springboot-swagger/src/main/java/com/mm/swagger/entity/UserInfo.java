package com.mm.swagger.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("用户基本信息")
public class UserInfo {
    
    public interface Default{}
    
    public interface Update{}

    @ApiModelProperty(value = "id", example = "1")
    @NotNull(message = "id不能为空", groups = {Default.class, Update.class})
    private Long id;

    @ApiModelProperty(value = "姓名", example = "宝宝")
    @Size(max = 20, message = "姓名长度错误", groups = Default.class)
    private String name;

    @ApiModelProperty(value = "年龄", example = "18")
    @Max(value = 150, message = "年龄大小错误", groups = Default.class)
    @Min(value = 1, message = "年龄大小不错误", groups = Default.class)
    private Integer age;

    @ApiModelProperty(value = "生日", example = "2018-08-08")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    @ApiModelProperty(value = "邮箱", example = "888888@qq.com")
    @Pattern(regexp = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$", message = "邮箱格式错误", groups = Default.class)
    private String email;

    @ApiModelProperty(value = "地址", example = "中国")
    @NotNull(message = "地址不能为空", groups = Default.class)
    private String address;

    @ApiModelProperty(value = "创建时间", example = "2018-08-08 08:08:08")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}
