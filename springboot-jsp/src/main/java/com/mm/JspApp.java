package com.mm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 启动：bootRun
 * (main方法启动无效)
 */
@SpringBootApplication
public class JspApp extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(JspApp.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(JspApp.class);
    }
}
