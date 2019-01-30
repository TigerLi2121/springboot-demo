package com.mm.api.advice;

import com.mm.api.common.R;
import com.mm.api.enums.REnum;
import com.mm.api.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(value = Exception.class)
    public R handle(Exception e) {
        if (e instanceof ServiceException) {
            ServiceException serviceException = (ServiceException) e;
            return R.error(serviceException.getREnum().getMsg());
        }else {
            log.error("【系统异常】:", e);
            return R.error(REnum.UNDER_EIGHTEEN.getMsg());
        }
    }
}
