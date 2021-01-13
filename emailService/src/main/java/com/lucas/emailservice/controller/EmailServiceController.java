package com.lucas.emailservice.controller;

import com.lucas.emailservice.dto.SendRawEmailReqData;
import com.lucas.emailservice.service.emailservice.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: lucasfen
 * @create: 2021/01/08
 */
@RestController
@RequestMapping("/emailService/v1")
public class EmailServiceController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/sendRawEmail")
    public void sendRawEmail(@RequestBody SendRawEmailReqData request) {
        emailService.sendRawEmail(request);
    }

}
