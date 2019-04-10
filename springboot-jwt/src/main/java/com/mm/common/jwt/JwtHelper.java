package com.mm.common.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.mm.common.utils.SpringContextUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * jwt信息
 *
 * @author lwl
 * @date 2018/9/11
 */
@Slf4j
public class JwtHelper {

    /**
     * 解析jwt
     */
    public static Map<String, Claim> parseJWT(String jsonWebToken, String base64Security) {
        try {
            return JWT.require(Algorithm.HMAC256(base64Security)).build().verify(jsonWebToken).getClaims();
        } catch (JWTVerificationException exception) {
            log.error("parseJWT:", exception);
            return null;
        }
    }

    /**
     * 构建jwt
     */
    public static String createJWT(String userId, String audience, String issuer, long TTLMillis, String base64Security) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        Map<String, Object> header = new HashMap<>();
        header.put("typ", "JWT");
        try {
            JWTCreator.Builder builder = JWT.create()
                    .withHeader(header)
                    .withClaim("userId", userId)
                    .withIssuer(issuer)
                    .withAudience(audience);
            //添加Token过期时间
            if (TTLMillis >= 0) {
                long expMillis = nowMillis + TTLMillis;
                Date exp = new Date(expMillis);
                builder.withExpiresAt(exp).withNotBefore(now);
            }
            return builder.sign(Algorithm.HMAC256(base64Security));
        } catch (JWTCreationException exception) {
            log.error("createJWT:", exception);
            return null;
        }
    }

    public static String getToken(Integer userId) {
        JwtBean jwtBean = SpringContextUtils.getBean("jwtBean", JwtBean.class);
        String token = "Bearer ";
        return token + JwtHelper.createJWT(
                userId.toString(),
                jwtBean.getClientId(),
                jwtBean.getName(),
                (long) jwtBean.getExpiresSecond() * 1000,
                jwtBean.getBase64Secret());
    }
}
