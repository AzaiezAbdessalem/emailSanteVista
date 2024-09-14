package com.EmailService.service.impl;

import com.EmailService.error.exception.MailNotSendException;
import com.EmailService.model.Mail;
import com.EmailService.service.IProxyMailService;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProxyMailServiceImpl implements IProxyMailService {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    public String from;

    /**
     * {@inheritDoc}
     */
    @Override
    @Async
    public void sendMail(Mail mailDetails) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8.name());
            messageHelper.setTo(mailDetails.getMailTo());
            messageHelper.setSubject(mailDetails.getSubject());
            messageHelper.setText(mailDetails.getBody(), true);
            messageHelper.setFrom(from);

            javaMailSender.send(mimeMessage);
            log.info("Sent email to {}", mailDetails.getMailTo());
        } catch (Exception e) {
            log.error("Failed to send email to {}", mailDetails.getMailTo(), e);
            throw new MailNotSendException("Failed to send email");
        }
    }
}
