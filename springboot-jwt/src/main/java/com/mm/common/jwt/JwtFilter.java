package com.mm.common.jwt;

import com.auth0.jwt.interfaces.Claim;
import com.mm.common.exception.ServiceException;
import com.mm.common.utils.Constant;
import com.mm.common.utils.SpringContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author lwl
 * @date 2018/9/11
 */
@Slf4j
public class JwtFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;
        //等到请求头信息Authorization信息
        final String authHeader = request.getHeader("Authorization");
        log.debug("Authorization:{}", authHeader);
        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            chain.doFilter(req, res);
        } else {

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                throw new ServiceException("登录失败", 886);
            }
            final String token = authHeader.substring(7);

            try {
                JwtBean jwtBean = SpringContextUtils.getBean("jwtBean", JwtBean.class);
                final Map<String, Claim> claims = JwtHelper.parseJWT(token, jwtBean.getBase64Secret());
                if (claims == null) {
                    throw new ServiceException("登录失败", 886);
                }
                log.info("claims:{}", claims);
                request.setAttribute("claims", claims);
                request.setAttribute(Constant.CURRENT_USER, claims.get("userId").asString());
            } catch (final Exception e) {
                throw new ServiceException("登录失败", 886);
            }

            chain.doFilter(req, res);
        }
    }
}
