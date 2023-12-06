package com.mm.api.aspect;

import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.json.JSONUtil;
import com.mm.api.exception.GException;
import com.mm.common.util.R;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 请求日志
 *
 * @author tigerli
 */
@Slf4j
@Aspect
@Component
public class ApiLogAspect {

    @Autowired
    private HttpServletRequest request;

    @Pointcut("execution(public * com.mm.api.web.*.*(..))")
    public void apiLog() {
    }

    @Around("apiLog()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        Object result = point.proceed();
        StringBuffer sb = getParam(point);
        sb.append("RESULT:").append(result).append(" ");
        sb.append("TIME:").append(System.currentTimeMillis() - beginTime).append("ms");
        log.debug("around:{}", sb);
        return result;
    }

    @AfterThrowing(value = "apiLog()", throwing = "e")
    public void afterThrowing(JoinPoint point, Exception e) {
        StringBuffer sb = getParam(point);
        String err = e.toString();
        if (e instanceof GException) {
            GException ge = (GException) e;
            err = JSONUtil.toJsonStr(R.error(ge.getCode(), ge.getMsg()));
        }
        sb.append("EXCEPTION:").append(err).append(" ");
        log.debug("afterThrowing:{}", sb);
    }

    private StringBuffer getParam(JoinPoint point) {
        StringBuffer sb = new StringBuffer(" ");
        sb.append("HTTP_METHOD:").append(request.getMethod()).append(" ");
        sb.append("URL:").append(request.getRequestURL().toString()).append(" ");
        sb.append("IP:").append(ServletUtil.getClientIP(request)).append(" ");
        sb.append("METHOD:").append(point.getTarget().getClass().getName()).append(".")
                .append(point.getSignature().getName()).append(" ");
        sb.append("PARAM:").append(JSONUtil.toJsonStr(point.getArgs())).append(" ");
        return sb;
    }

}
