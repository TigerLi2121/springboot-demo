package com.mm.common.exception;

import com.mm.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常处理器
 */
@Slf4j
@RestControllerAdvice
public class ServiceExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public R handle(Exception e) {
        if (e instanceof ServiceException) {
            ServiceException ServiceException = (ServiceException) e;
            return R.error(ServiceException.getMsg());
        } else {
            log.error("【系统异常】:", e);
            return R.error("系统异常");
        }
    }
}
