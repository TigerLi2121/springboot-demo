package com.mm.api.exception;

import com.mm.api.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

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
            String msg = be.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
            return R.error(100, msg);
        } else if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException me = (MethodArgumentNotValidException) e;
            String msg = me.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
            return R.error(100, msg);
        } else if (e instanceof ConstraintViolationException) {
            ConstraintViolationException ce = (ConstraintViolationException) e;
            String msg = ce.getConstraintViolations().stream().map(m -> m.getMessage())
                    .collect(Collectors.toList()).get(0);
            return R.error(100, msg);
        } else {
            log.error("【系统异常】:", e);
            return R.error("系统异常");
        }
    }
}
