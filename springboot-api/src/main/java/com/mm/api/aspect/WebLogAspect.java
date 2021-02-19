package com.mm.api.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 请求日志
 *
 * @author lwl
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

        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        StringBuffer sb = new StringBuffer("\n");
        sb.append("HTTP_METHOD:").append(request.getMethod()).append("\n");
        sb.append("URL:").append(request.getRequestURL().toString()).append("\n");
        sb.append("IP:").append(request.getRemoteAddr()).append("\n");
        sb.append("METHOD:").append(point.getTarget().getClass().getName()).append(".")
                .append(point.getSignature().getName()).append("\n");
        sb.append("PARAM:").append(point.getArgs().length > 0 ? point.getArgs()[0] : "").append("\n");
        sb.append("TIME:").append(System.currentTimeMillis() - beginTime);
        log.debug("{}", sb.toString());
        return result;
    }
}
