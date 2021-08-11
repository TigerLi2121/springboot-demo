package com.mm.api.aspect;

import com.mm.api.common.HttpContextUtil;
import com.mm.api.exception.GException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 请求日志
 *
 * @author shmily
 */
@Slf4j
@Aspect
@Component
public class WebLogAspect {

    @Pointcut("execution(public * com.mm.api.web.*.*(..))")
    public void webLog() {
    }

    @Around("webLog()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        Object result = point.proceed();
        StringBuffer sb = getParam(point);
        sb.append("RESULT:").append(result).append("\n");
        sb.append("TIME:").append(System.currentTimeMillis() - beginTime);
        log.debug("around:{}", sb);
        return result;
    }

    @AfterThrowing(value = "webLog()", throwing = "e")
    public void afterThrowing(JoinPoint point, Exception e) {
        StringBuffer sb = getParam(point);
        String err = e.toString();
        if (e instanceof GException) {
            err = ((GException) e).getMsg();
        }
        sb.append("EXCEPTION:").append(err).append("\n");
        log.debug("afterThrowing:{}", sb);
    }

    private StringBuffer getParam(JoinPoint point) {
        HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
        StringBuffer sb = new StringBuffer("\n");
        sb.append("HTTP_METHOD:").append(request.getMethod()).append("\n");
        sb.append("URL:").append(request.getRequestURL().toString()).append("\n");
        sb.append("IP:").append(request.getRemoteAddr()).append("\n");
        sb.append("METHOD:").append(point.getTarget().getClass().getName()).append(".")
                .append(point.getSignature().getName()).append("\n");
        sb.append("PARAM:").append(point.getArgs().length > 0 ? point.getArgs()[0] : "").append("\n");
        return sb;
    }

}
