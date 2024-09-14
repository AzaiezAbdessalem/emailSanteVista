package com.EmailService.service.impl;



import com.EmailService.model.Mail;
import com.EmailService.service.IEmailSenderService;
import com.EmailService.service.IProxyMailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Service
@RequiredArgsConstructor
public class EmailSenderServiceImpl implements IEmailSenderService {

    private static final String TEMPLATE_NAME = "activeAccount";
    private static final String TEMPLATE_NAME_VERIFICATION_CODE = "verifAccountCode";
    private final SpringTemplateEngine htmlTemplateEngine;
    private final IProxyMailService proxyMailService;
    @Value("${urlApp}")
    private String appUrl;

    @Override
    public void sendResetPasswordMail(Mail mail) {
        String confirmationUrl = String.format("%s?email=%s", appUrl, mail.getMailTo());
        Context ctx = new Context(LocaleContextHolder.getLocale());
        ctx.setVariable("verificationCode", mail.getBody());
        ctx.setVariable("url", confirmationUrl);
        final String htmlContent = htmlTemplateEngine.process(TEMPLATE_NAME_VERIFICATION_CODE, ctx);
        mail.setBody(htmlContent);
        proxyMailService.sendMail(mail);
    }

    @Override
    public void activatedAccount(Mail mail) {
        Context ctx = new Context(LocaleContextHolder.getLocale());
        ctx.setVariable("url", appUrl + "/login");

        final String htmlContent = htmlTemplateEngine.process(TEMPLATE_NAME, ctx);
        mail.setBody(htmlContent);
        proxyMailService.sendMail(mail);
    }


}
