package com.mm.api.exception;

import lombok.Getter;

@Getter
public class GException extends RuntimeException {
    private String msg;
    private int code = 500;

    public GException(String msg) {
        this.msg = msg;
    }

    public GException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
