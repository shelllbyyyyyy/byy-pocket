package com.shelby.ByyPocket.modules.user.application.usecase;

import com.shelby.ByyPocket.common.exceptions.NoSuchElementFoundException;
import com.shelby.ByyPocket.modules.user.domain.entities.UserEntity;
import com.shelby.ByyPocket.modules.user.domain.factories.UserMapper;
import com.shelby.ByyPocket.modules.user.domain.services.UserService;
import com.shelby.ByyPocket.modules.user.presentation.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.shelby.ByyPocket.common.Constants.NOT_FOUND;

@Service
@Slf4j(topic = "Find By Email")
@RequiredArgsConstructor
public class FindByEmail {
    private final UserService userService;

    public Optional<UserResponse> execute(String email) {
        final Optional<UserEntity> result = userService.findByEmail(email);

        if (result.isEmpty()) {
            log.info(NOT_FOUND);
            throw new NoSuchElementFoundException(NOT_FOUND);
        }


        return result.map(UserMapper::toResponse);
    }
}
