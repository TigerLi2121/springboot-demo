package com.mm.api.exception;

import lombok.Getter;

@Getter
public class MMException extends RuntimeException {
    private String msg;
    public MMException(String msg) {
        this.msg = msg;
    }
}
