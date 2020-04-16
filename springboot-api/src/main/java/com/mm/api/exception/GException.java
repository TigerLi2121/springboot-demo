package com.mm.api.exception;

import lombok.Getter;

@Getter
public class GException extends RuntimeException {
    private String msg;
    public GException(String msg) {
        this.msg = msg;
    }
}
