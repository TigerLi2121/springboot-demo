package com.mm.common.config;

import com.mm.common.jwt.JwtFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Filter配置
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean jwtFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new JwtFilter());
        registration.setName("jwtFilter");
        registration.setOrder(Integer.MAX_VALUE);
        List<String> patterns = new ArrayList<>();
        patterns.add("/*");
        registration.addUrlPatterns(patterns.toArray(new String[patterns.size()]));
        registration.setEnabled(true);
        return registration;
    }
}
