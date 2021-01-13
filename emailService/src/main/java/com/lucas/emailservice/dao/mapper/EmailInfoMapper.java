package com.lucas.emailservice.dao.mapper;

import com.lucas.emailservice.dao.entity.EmailInfo;

public interface EmailInfoMapper {

    int addEmailInfo(EmailInfo emailInfo);

    EmailInfo selectEmailInfo(String receiverEmail);
}
