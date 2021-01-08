package com.lucas.emailservice.service.emailservice.handler;

import java.util.List;

import com.lucas.emailservice.dto.SendRawEmailReqData;
import com.lucas.emailservice.service.emailservice.EmailHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: lucasfen
 * @create: 2021/01/08
 */
@Component
public class SendRawEmailHandler {

    @Autowired
    private EmailHelper emailHelper;

    public void handleSendRawEmail(SendRawEmailReqData request) {
        List<String> receiverEmailList = request.getReceiverEmailList();
        String senderEmail = request.getSenderEmail();
        String emailSubject = request.getEmailSubject();
        String emailContent = request.getEmailContent();

        for (String receiverEmail : receiverEmailList) {
            if (!emailHelper.filterEmail(receiverEmail)) {
                return;
            }
            emailHelper.sendHtmlEmailJava(senderEmail, receiverEmail, emailContent, emailSubject);
        }
    }
}
