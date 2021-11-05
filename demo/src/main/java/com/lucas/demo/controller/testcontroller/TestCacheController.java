package com.lucas.demo.controller.testcontroller;

import com.lucas.demo.dto.User;
import com.lucas.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lucasfen
 * @Date 2021/06/19
 */
@RestController
@RequestMapping("/test/cache")
public class TestCacheController {

    @Autowired
    private UserService userService;

    @PostMapping("/testCache")
    public void testCache() {
        User user1 = new User();
        user1.setId(1);
        user1.setName("lucas");
        userService.addUser(user1);
        User user2 = new User();
        user2.setId(2);
        user2.setName("fen");
        userService.addUser(user2);
        userService.getUser(1);
        userService.getUser(1);
        userService.getUser(2);
        userService.getUser(2);
    }
}
