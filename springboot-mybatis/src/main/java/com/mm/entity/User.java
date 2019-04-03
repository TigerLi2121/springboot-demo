package com.mm.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    private String password;

    private String nickName;

    private Gender gender;

    public enum Gender {
        MALE, FEMALE
    }

    private Date gmtCreate;

    private Date gmtModified;

}
