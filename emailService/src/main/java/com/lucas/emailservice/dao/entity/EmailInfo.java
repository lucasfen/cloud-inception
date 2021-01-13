package com.lucas.emailservice.dao.entity;

import lombok.Data;

/**
 * @author: lucasfen
 * @create: 2021/01/13
 */
@Data
public class EmailInfo {

    private Integer id;

    private String receiverEmail;

    private String senderEmail;

    private String subject;

    public EmailInfo(String receiverEmail, String senderEmail, String subject) {
        this.receiverEmail = receiverEmail;
        this.senderEmail = senderEmail;
        this.subject = subject;
    }
}
