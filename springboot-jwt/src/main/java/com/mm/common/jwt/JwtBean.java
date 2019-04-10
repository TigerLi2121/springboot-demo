package com.mm.common.jwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * jwt 配置
 *
 * @author lwl
 * @date 2018/9/11
 */
@Data
@ConfigurationProperties(prefix = "jwt")
@Component
public class JwtBean {
    private String clientId;
    private String base64Secret;
    private String name;
    private int expiresSecond;
}
