package com.mm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 主从，读写分离
 * mybatis-plus sharding-jdbc
 *
 * @author lwl
 */
@SpringBootApplication
@MapperScan("com.mm.test.mapper")
public class RwsApp {
    public static void main(String[] args) {
        SpringApplication.run(RwsApp.class, args);
    }
}
