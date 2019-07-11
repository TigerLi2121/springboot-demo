package com.mm.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@Document
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

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

}
