package com.lucas.demo.controller.testcontroller;

import java.util.*;
import java.util.stream.Collectors;

import com.lucas.demo.dto.User;
import com.lucas.demo.service.StreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Lucasfen
 * @Date 2021/07/01
 */
@RestController
@RequestMapping("/test/stream")
public class TestStreamController {

    @Autowired
    private StreamService streamService;

    @PostMapping("/list/sort")
    public void testListSortStream() {
        User user1 = new User();
        user1.setId(1);
        user1.setName("user1");
        user1.setTime((long)12345678);
        User user2 = new User();
        user2.setId(2);
        user2.setName("user2");
        user2.setTime((long)12345676);
        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        System.out.print(userList);
        List<User> usernewList = userList.stream().sorted(Comparator.comparing(User::getTime).reversed()).collect(Collectors.toList());
        System.out.println(usernewList);
    }

    @PostMapping("/iterator")
    public void testIterator() {
        Map<String, String> result = new HashMap<>();
        result.put("key1", "value1");
        result.put("key2", "value2");
        Iterator<Map.Entry<String, String>> iterator = result.entrySet().iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().getKey());
            iterator.remove();
        }
    }


}
