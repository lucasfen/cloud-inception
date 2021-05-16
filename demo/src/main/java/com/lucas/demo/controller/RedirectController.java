package com.lucas.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: lucasfen
 * @create: 2021/02/01
 */
@RestController
@RequestMapping(path="/demo/redirect")
public class RedirectController {

    @GetMapping("/test")
    public String test() {
        return "redirect:https://www.baidu.com";
    }

}
