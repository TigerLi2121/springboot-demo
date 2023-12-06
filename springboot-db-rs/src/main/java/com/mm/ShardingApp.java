package com.mm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 单库分表
 * mybatis-plus sharding-jdbc
 *
 * @author lwl
 */
@MapperScan(basePackages = "com.mm.test.dao")
@SpringBootApplication
public class ShardingApp {
    public static void main(String[] args) {
        SpringApplication.run(ShardingApp.class, args);
    }
}
