package com.lucas.emailservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: lucasfen
 * @create: 2021/01/08
 */
@SpringBootApplication
@MapperScan(basePackages = "com.lucas.emailservice.dao.mapper")
public class EmailApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmailApplication.class, args);
    }
}
