package com.mm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 */
@SpringBootApplication
@MapperScan("com.mm.dao")
public class MyBatisPlusApp {
    public static void main(String[] args) {
        SpringApplication.run(MyBatisPlusApp.class, args);
    }
}
