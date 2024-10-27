package com.shelby.ByyPocket.shared.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "Email Service")
public class IEmailService {

    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine templateEngine;

    @Value("${app.mail.admin}")
    private String sender;

    public void sendSimpleMail(EmailDTO data, Map<String, Object> templateModel, String template)  {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(data.getRecipient());
            helper.setSubject(data.getSubject());

            Context thymeleafContext = new Context();
            thymeleafContext.setVariables(templateModel);

            String htmlBody = templateEngine.process(template, thymeleafContext);

            helper.setText(htmlBody, true);
            helper.setFrom(sender);

            javaMailSender.send(message);
        } catch (MessagingException e) {
            log.info("Error send email: ", e);
        }

    }
}