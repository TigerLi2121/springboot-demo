package com.mm.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户
 *
 * @author tigerli
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table("user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id(keyType = KeyType.Auto)
    private Long id;

    private String username;

    private String password;

    private String nickName;

    private Gender gender;
    @Column(onInsertValue = "now()")
    private Date createTime;
    @Column(onInsertValue = "now()", onUpdateValue = "now()")
    private Date updateTime;

    public enum Gender {
        MALE, FEMALE
    }

}
