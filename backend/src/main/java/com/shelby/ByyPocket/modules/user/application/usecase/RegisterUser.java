package com.shelby.ByyPocket.modules.user.application.usecase;

import com.shelby.ByyPocket.common.exceptions.ElementAlreadyExistsException;
import com.shelby.ByyPocket.common.securities.JwtUtils;
import com.shelby.ByyPocket.modules.user.domain.events.SendUserWelcome;
import com.shelby.ByyPocket.modules.user.domain.events.SendVerificationLink;
import com.shelby.ByyPocket.modules.user.presentation.dto.request.RegisterUserRequest;
import com.shelby.ByyPocket.common.responses.CommandResponse;
import com.shelby.ByyPocket.modules.user.domain.entities.UserEntity;
import com.shelby.ByyPocket.modules.user.domain.services.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.shelby.ByyPocket.common.Constants.ALREADY_EXISTS_USER_EMAIL;

@Service
@Slf4j(topic = "Register User")
@RequiredArgsConstructor
public class RegisterUser {
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final SendVerificationLink sendVerificationLink;
    private final UserService userService;

    public CommandResponse execute(RegisterUserRequest data) {
        if (userService.findByEmail(data.getEmail()).isPresent()) {
            log.info(ALREADY_EXISTS_USER_EMAIL);
            throw new ElementAlreadyExistsException(ALREADY_EXISTS_USER_EMAIL);
        }

        String hashedPassword = passwordEncoder.encode(data.getPassword());

        final UserEntity user = userService.createUser(data.getUsername(), data.getEmail(), hashedPassword);

        final String token = jwtUtils.generateTokenLink(user.getEmail());

        sendVerificationLink.execute(user.getEmail(), user.getUsername(), token);

        return CommandResponse.builder().id(user.getId()).build();
    };
}
