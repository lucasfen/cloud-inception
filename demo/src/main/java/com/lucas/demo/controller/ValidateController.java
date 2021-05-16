package com.lucas.demo.controller;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import com.lucas.demo.dto.User;
import com.lucas.demo.service.ValidateService;
import com.lucas.demo.utils.ListToPageUtil;
import com.lucas.demo.validation.IotValidator;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;

/**
 * @author Lucasfen
 * @Date 2021/05/08
 */
@RestController
@RequestMapping(path = "/demo/validate")
public class ValidateController {

    @Resource
    private ValidateService validateService;

    @PostMapping(path = "/test")
    public void test(@RequestBody ValidateService.ValidateTestReq request) {

    }

    @PostMapping(path = "/testPage")
    public Page<User> testPage() {
        List<User> userList = new ArrayList<>();
        userList.add(new User("1号", "11"));
        userList.add(new User("2号", "22"));
        Pageable pageable = PageRequest.of(1, 2);
        Page<User> page = ListToPageUtil.listConvertToPage(userList, pageable);
        return page;
    }
}
