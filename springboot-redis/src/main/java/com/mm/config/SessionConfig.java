package com.mm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author: shmily
 * @date: 2018/2/13 17:23
 **/
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 86400) //Session失效时间 默认1800
public class SessionConfig {
}
