package com.disertatie.mail.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class SentMail {
    private String sender;
    private String receiver;
    private String subject;
    private String mailBody;

}
