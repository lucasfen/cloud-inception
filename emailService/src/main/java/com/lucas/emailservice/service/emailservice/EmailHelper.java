package com.lucas.emailservice.service.emailservice;

import java.util.Map;
import java.util.regex.Pattern;

import javax.mail.internet.MimeMessage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

/**
 * @author: lucasfen
 * @create: 2021/01/08
 */
@Slf4j
@Component
public class EmailHelper {

    @Autowired
    private JavaMailSenderImpl mailSender;

    @Value("#{${account.pws.map}}")
    private Map<String, String> senderEmailAccountPwsMap;

    @Value("#{${account.host.map}}")
    private Map<String, String> senderEmailAccountHostMap;

    @Value("${test.email.pattern}")
    private String testPattern;

    /**
     * 使用Java SpringBoot SDK 发送邮件
     *
     * @param from     发件人邮箱
     * @param to       收件人邮箱
     * @param emailContent 邮件正文
     * @param subject  邮件主题
     */
    public void sendHtmlEmailJava(String from, String to, String emailContent, String subject) {
        mailSender.setUsername(from);
        mailSender.setPassword(senderEmailAccountPwsMap.get(from));
        mailSender.setHost(senderEmailAccountHostMap.get(from));
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(emailContent, true);
            mailSender.send(mimeMessage);
            log.info("Java Email sent. from: {}, to: {}, subject: {}, messageId: {}",
                    from, to, subject, mimeMessage.getMessageID());
        } catch (Exception e) {
            log.error("fail to send email, from: {}, to: {}, subject: {}", from, to, subject);
            throw new RuntimeException(e);
        }
    }

    /**
     * 过滤掉测试邮件邮件
     *
     * @param receiverEmail 收件人邮箱
     * @return
     */
    public boolean filterEmail(String receiverEmail) {

        //假的测试邮箱，不发送
        Pattern pattern = Pattern.compile(testPattern);
        if (pattern.matcher(receiverEmail).matches()) {
            log.info("receiverEmail: {}, test email will not be sent", receiverEmail);
            return false;
        }
        return true;
    }
}
