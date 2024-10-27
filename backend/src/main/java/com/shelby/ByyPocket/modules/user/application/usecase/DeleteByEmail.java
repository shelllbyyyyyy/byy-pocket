package com.shelby.ByyPocket.modules.user.application.usecase;

import com.shelby.ByyPocket.modules.user.domain.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.shelby.ByyPocket.common.Constants.SUCCESS;


@Service
@Slf4j(topic = "Delete By Email")
@RequiredArgsConstructor
public class DeleteByEmail {
    private final UserService userService;

    public boolean execute(String email) {
        userService.delete(email);
        log.info(SUCCESS + "Delete account");
        return true;
    }
}

