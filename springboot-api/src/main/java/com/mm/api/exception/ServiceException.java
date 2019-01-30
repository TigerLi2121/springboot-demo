package com.mm.api.exception;

import com.mm.api.enums.REnum;
import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException {
    private REnum rEnum;
    public ServiceException(REnum rEnum) {
        this.rEnum = rEnum;
    }
}
