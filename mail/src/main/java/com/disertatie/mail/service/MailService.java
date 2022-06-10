package com.disertatie.mail.service;

import com.disertatie.mail.mail.MailProperties;
import com.disertatie.mail.model.SentMail;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


@Service
@AllArgsConstructor
@Log4j2
public class MailService {

    private final MailProperties mailProperties;
    private static final Logger log = LoggerFactory.getLogger(MailService.class);

    public SentMail getSentMailDetails(String receiver, String subject, String content) {
        sendMail(receiver, subject, content);
        return SentMail.builder()
                .sender(mailProperties.getAddress()) //could be emailAddress instead of address
                .receiver(receiver)
                .subject(subject)
                .mailBody(content)
                .build();
    }

    private Session getSession() {
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", mailProperties.getHost());
        properties.put("mail.smtp.port", mailProperties.getPort());
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.trust", mailProperties.getHost());
        properties.put("mail.smtp.starttls.enable", "true");

        // Get the Session object and pass username and password
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailProperties.getAddress(), mailProperties.getPwd());
            }
        });
        return session;
    }

    private void sendMail(String receiver, String subject, String content) {
        Session session = getSession();
        try {
            //MimeMessage message = new MimeMessage(session); or Email mail = new SimpleEmail();
            MimeMessage message = new MimeMessage(session);

            // Set From:
            message.setFrom(new InternetAddress(mailProperties.getAddress()));

            // Set To:
            message.addRecipients(Message.RecipientType.TO, receiver);

            // Set Subject:
            message.setSubject(subject);

            // Set the actual message:
            message.setText(content);


            log.info("Sending email to: {}", receiver);
            // Send message:
            log.info("message {} ", message);
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}

