package com.lucas.emailservice.service.emailservice;

import com.lucas.emailservice.dto.SendRawEmailReqData;

public interface EmailService {

    void sendRawEmail(SendRawEmailReqData request);
}
