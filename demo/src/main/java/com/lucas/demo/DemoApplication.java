package com.lucas.demo;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author lucas
 * @create 2021/5/17
 */
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableCaching
@EnableAsync
@EnableScheduling
@ComponentScan("com.lucas")
public class DemoApplication {

    @Value("#{'${test.list}'.split(',')}")
    private List<String> testList;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @PostConstruct
    public void getList() {
        System.out.println(testList);
    }
}
