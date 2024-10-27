package com.shelby.ByyPocket.modules.user.domain.events;

import com.shelby.ByyPocket.shared.email.EmailDTO;
import com.shelby.ByyPocket.shared.email.IEmailService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class SendUserWelcome {
    private final IEmailService service;

    public void execute(String recipient, String username) {
        final String body = "Welcome to Byy-Pocket";
        final String subject = "Welcome new user";
        final String template = "send-user-welcome.html";

        Map<String, Object> templateModel = new HashMap<>();
        templateModel.put("username", username);
        templateModel.put("email", recipient);

        final EmailDTO data = new EmailDTO();
        data.setRecipient(recipient);
        data.setBody(body);
        data.setSubject(subject);

        service.sendSimpleMail(data, templateModel, template);
    }
}
