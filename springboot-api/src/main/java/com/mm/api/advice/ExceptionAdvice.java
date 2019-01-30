package com.mm.api.advice;

import com.mm.api.common.R;
import com.mm.api.enums.ResultEnum;
import com.mm.api.exception.ServiceException;
import com.mm.api.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(value = Exception.class)
    public R handle(Exception e) {
        if (e instanceof ServiceException) {
            ServiceException serviceException = (ServiceException) e;
            return ResponseEntity.ok(ResultUtil.resultEnum(serviceException.getResultEnum()));
        }else {
            log.error("【系统异常】:", e);
            return ResponseEntity.ok(ResultUtil.resultEnum(ResultEnum.UNKONW_ERROR));
        }
    }
}
