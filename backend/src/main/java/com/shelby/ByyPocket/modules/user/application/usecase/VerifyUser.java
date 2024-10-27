package com.shelby.ByyPocket.modules.user.application.usecase;

import com.shelby.ByyPocket.common.exceptions.ExpiredJwtException;
import com.shelby.ByyPocket.common.exceptions.NoSuchElementFoundException;
import com.shelby.ByyPocket.common.securities.JwtUtils;
import com.shelby.ByyPocket.modules.user.domain.events.SendUserWelcome;
import com.shelby.ByyPocket.common.responses.CommandResponse;
import com.shelby.ByyPocket.modules.user.domain.entities.UserEntity;
import com.shelby.ByyPocket.modules.user.domain.services.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.shelby.ByyPocket.common.Constants.NOT_FOUND_RECORD;

@Service
@Slf4j(topic = "Verify User")
@RequiredArgsConstructor
public class VerifyUser {
    private final JwtUtils jwtUtils;
    private final SendUserWelcome sendUserWelcome;
    private final UserService userService;

    public CommandResponse execute(String token) {
        String verify;

        if (jwtUtils.validateUser(token)) {
            final String email = jwtUtils.getEmailFromJwtToken(token);

            final Optional<UserEntity> user = userService.findByEmail(email);
            if (user.isEmpty()) {
                log.info(NOT_FOUND_RECORD);
                throw new NoSuchElementFoundException(NOT_FOUND_RECORD);
            }

            verify = userService.verifyUser(user.get());
            sendUserWelcome.execute(user.get().getEmail(), user.get().getUsername());
        } else {
            throw new ExpiredJwtException("Token has been expired");
        }
        
        return CommandResponse.builder().id(verify).build();
    };
}
