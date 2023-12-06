package com.mm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * mybatis-flex
 *
 * @author lwl
 */
@MapperScan("com.mm.mapper")
@SpringBootApplication
public class MybatisFlexApp {
    public static void main(String[] args) {
        SpringApplication.run(MybatisFlexApp.class, args);
    }
}