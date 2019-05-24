package com.mm.api.exception;

import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException {
    private String msg;
    public ServiceException(String msg) {
        this.msg = msg;
    }
}
