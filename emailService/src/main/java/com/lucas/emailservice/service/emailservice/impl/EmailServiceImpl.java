package com.lucas.emailservice.service.emailservice.impl;

import com.google.gson.JsonObject;
import com.lucas.emailservice.dto.SendRawEmailReqData;
import com.lucas.emailservice.service.emailservice.EmailService;
import com.lucas.emailservice.service.emailservice.handler.SendRawEmailHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: lucasfen
 * @create: 2021/01/08
 */
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private SendRawEmailHandler sendRawEmailHandler;

    @Override
    public void sendRawEmail(SendRawEmailReqData request) {
        sendRawEmailHandler.handleSendRawEmail(request);
    }
}
