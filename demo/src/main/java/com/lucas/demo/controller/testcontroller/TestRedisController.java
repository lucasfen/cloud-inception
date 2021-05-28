package com.lucas.demo.controller.testcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lucasfen
 * @Date 2021/05/26
 */
@RestController
@RequestMapping("/test/redis")
public class TestRedisController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping("/connect")
    public void testConnectRedis() {
        System.out.println(stringRedisTemplate.opsForValue().get("user"));
    }
}
