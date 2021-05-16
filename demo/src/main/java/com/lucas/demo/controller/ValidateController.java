package com.lucas.demo.controller;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import com.lucas.demo.dto.User;
import com.lucas.demo.service.DalService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    private DalService dalService;

    @PostMapping(path = "/testDal")
    public void testTransaction() {
        dalService.testDal();
    }
}
