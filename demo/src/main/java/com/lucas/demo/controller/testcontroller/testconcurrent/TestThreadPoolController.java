package com.lucas.demo.controller.testcontroller.testconcurrent;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lucasfen
 * @Date 2021/09/01
 */
@RestController
@RequestMapping("/test/threadpool")
public class TestThreadPoolController {

    @PostMapping
    public void testThreadPool() {

    }
}
