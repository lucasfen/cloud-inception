package com.lucas.emailservice.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;


/**
 * @author: lucasfen
 * @create: 2021/01/08
 */
@Data
public class SendRawEmailReqData {

    @NotEmpty(message = "receiverEmail list should not be empty")
    private List<String> receiverEmailList;

    @NotEmpty(message = "senderEmail should not be empty")
    private String senderEmail;

    @NotNull(message = "emailSubject should not be null")
    private String emailSubject;

    private String emailContent;
}
