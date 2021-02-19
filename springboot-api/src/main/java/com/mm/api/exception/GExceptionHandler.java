package com.mm.api.exception;

import com.mm.api.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.bind.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public R handle(Exception e) {
        if (e instanceof GException) {
            GException ge = (GException) e;
            return R.error(ge.getMsg());
        } else if (e instanceof BindException) {
            BindException be = (BindException) e;
            String msg = be.getMessage();
            return R.error(100, msg);
        } else {
            log.error("【系统异常】:", e);
            return R.error("系统异常");
        }
    }
}
