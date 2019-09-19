package com.mm.common.exception;

import com.mm.common.utils.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常处理器
 */
@RestControllerAdvice
public class BadExceptionHandler {

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(BadException.class)
    public R handleBadException(BadException e) {
        R r = new R();
        r.put("code", e.getCode());
        r.put("msg", e.getMessage());
        return r;
    }
}
