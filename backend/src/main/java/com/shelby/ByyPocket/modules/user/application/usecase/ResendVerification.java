package com.shelby.ByyPocket.modules.user.application.usecase;

import com.shelby.ByyPocket.common.exceptions.NoSuchElementFoundException;
import com.shelby.ByyPocket.common.securities.JwtUtils;
import com.shelby.ByyPocket.modules.user.domain.events.ResendVerificationLink;

import com.shelby.ByyPocket.modules.user.domain.entities.UserEntity;
import com.shelby.ByyPocket.modules.user.domain.services.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.shelby.ByyPocket.common.Constants.NOT_FOUND_RECORD;

@Service
@Slf4j(topic = "Resend Verification")
@RequiredArgsConstructor
public class ResendVerification {
    private final JwtUtils jwtUtils;
    private final ResendVerificationLink resendVerificationLink;
    private final UserService userService;

    public void execute(String email) {
        final Optional<UserEntity> user = userService.findByEmail(email);
        if (user.isEmpty()) {
            log.info(NOT_FOUND_RECORD);
            throw new NoSuchElementFoundException(NOT_FOUND_RECORD);
        }

        final String token = jwtUtils.generateTokenLink(user.get().getEmail());
        resendVerificationLink.execute(user.get().getEmail(), user.get().getUsername(), token);
    };
}
