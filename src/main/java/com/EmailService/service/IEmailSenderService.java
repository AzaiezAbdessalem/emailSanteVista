package com.EmailService.service;


import com.EmailService.model.Mail;

public interface IEmailSenderService {

    void sendResetPasswordMail(Mail mail);

    void activatedAccount(Mail mail);

}
