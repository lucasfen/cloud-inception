package com.lucas.demo.service;

import java.util.HashMap;
import java.util.Map;

import com.lucas.demo.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author Lucasfen
 * @Date 2021/06/19
 */
@Service
@Slf4j
public class UserService {

    private Map<Integer, User> userMap = new HashMap<>();
    public void addUser(User user) {
        log.info("create");
        userMap.put(user.getId(), user);
    }

    @Cacheable(value="emailTemplateCacheManager")
    public User getUser(Integer id) {
        log.info("get, id: {}, {}", id);
        User user = userMap.get(id);
        return user;
    }
}
