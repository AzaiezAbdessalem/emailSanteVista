package com.EmailService.service;


import com.EmailService.model.Mail;

public interface IProxyMailService {

    void sendMail(Mail mailDetails);
}
