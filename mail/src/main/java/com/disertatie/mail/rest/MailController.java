package com.disertatie.mail.rest;


import com.disertatie.mail.model.SentMail;
import com.disertatie.mail.service.MailService;
import com.disertatie.mail.service.SpringMailService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/skimanagement/api")
public class MailController {
    private final MailService mailService;
    private final SpringMailService springMailService;

    private static final Logger log = LoggerFactory.getLogger(MailController.class);

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/v3/send/email")
    public ResponseEntity<SentMail> sendFirstVarEmail(@RequestParam(name = "receiver") String receiver,
                                                      @RequestParam(name = "subject") String subject,
                                                      @RequestParam(name = "body") String body) {
        return ResponseEntity.ok(mailService.getSentMailDetails(receiver, subject, body));
    }

    @GetMapping("/v3/send/email-v2")
    public ResponseEntity<SentMail> sendSecondVarEmail(@RequestParam(name = "receiver") String receiver,
                                                       @RequestParam(name = "subject") String subject,
                                                       @RequestParam(name = "body") String body) {
        return ResponseEntity.ok(springMailService.getSentMailDetails(receiver, subject, body));
    }
}
