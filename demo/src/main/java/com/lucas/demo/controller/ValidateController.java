package com.lucas.demo.controller;

import javax.annotation.Resource;


import com.lucas.demo.service.ValidateService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Lucasfen
 * @Date 2021/05/08
 */
@RestController
@RequestMapping(path = "/demo/validate")
public class ValidateController {

    @Resource
    private ValidateService validateService;

    @PostMapping("/test")
    public void test() {
        validateService.test();
    }
}
