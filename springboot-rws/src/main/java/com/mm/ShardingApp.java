package com.mm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * mybatis-plus sharding-jdbc read write splitting
 *
 * @author lwl
 */
@SpringBootApplication
@MapperScan("com.mm.test.mapper")
public class ShardingApp {
    public static void main(String[] args) {
        SpringApplication.run(ShardingApp.class, args);
    }
}
