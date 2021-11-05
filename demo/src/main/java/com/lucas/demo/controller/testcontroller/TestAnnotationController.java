package com.lucas.demo.controller.testcontroller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lucasfen
 * @Date 2021/06/07
 */
@RestController
@RequestMapping("/test/annotation")
public class TestAnnotationController {

    @PostMapping("/before")
    public void testAnnotation() {
        System.out.println("method run");
    }
}
