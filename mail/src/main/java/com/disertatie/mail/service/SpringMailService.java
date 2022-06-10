package com.disertatie.mail.service;


import com.disertatie.mail.model.SentMail;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Service
@Log4j2
public class SpringMailService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${app.email.user}")
    private String user;

    public SentMail getSentMailDetails (String receiver, String subject, String content) {
        send(receiver, subject, content);
        return SentMail.builder()
                .sender(user)
                .receiver(receiver)
                .subject(subject)
                .mailBody(content)
                .build();
    }

    @Async
    private void send(String receiver, String subject, String content) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setFrom(user); //user -> to
            helper.setTo(receiver); //receiver -> from
            helper.setSubject(subject);
            helper.setText(content);
            log.info("Sending email from {}, to {}, with subject {} and content {}", user, receiver, subject, content);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            log.error("Failed to send email to: {}", receiver);
            e.printStackTrace();
        }
    }
}
