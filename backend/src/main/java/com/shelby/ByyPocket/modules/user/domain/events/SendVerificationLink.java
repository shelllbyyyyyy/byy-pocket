package com.shelby.ByyPocket.modules.user.domain.events;

import com.shelby.ByyPocket.common.securities.JwtUtils;
import com.shelby.ByyPocket.shared.email.EmailDTO;
import com.shelby.ByyPocket.shared.email.IEmailService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class SendVerificationLink {
    private final IEmailService service;

    @Value("${app.client.baseUrl}")
    private String baseUrl;

    public void execute(String recipient, String username, String token) {
        final String body = "Verify your account";
        final String subject = "Verify account";
        final String template = "verify-account.html";

        Map<String, Object> templateModel = new HashMap<>();
        templateModel.put("username", username);
        templateModel.put("confirmation_url", baseUrl + "/auth/activate-account/" + recipient + "/" + token);

        final EmailDTO data = new EmailDTO();
        data.setRecipient(recipient);
        data.setBody(body);
        data.setSubject(subject);

        service.sendSimpleMail(data, templateModel, template);
    }
}
