package com.EmailService.controller;

import com.EmailService.model.Mail;
import com.EmailService.service.IEmailSenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/mail")
@RequiredArgsConstructor
@Slf4j
public class EmailController {

    private final IEmailSenderService emailSenderService;

    @PostMapping("/resetPassword")
    public ResponseEntity<Void> sendResetPasswordMail(@RequestBody Mail mail) {
        log.info("Sending reset mail to {}", mail.getMailTo());
        emailSenderService.sendResetPasswordMail(mail);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/activatedAccount")
    public ResponseEntity<Void> activatedAccount(@RequestBody Mail mail) {
        log.info("Sending activated account mail to {}", mail.getMailTo());
        emailSenderService.activatedAccount(mail);
        return ResponseEntity.ok().build();
    }





}

