package com.EmailService.model;


import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Mail {
    private String typeMail;
    private String fullname;
    private String mailTo;
    private String body;
    private String subject;

}
