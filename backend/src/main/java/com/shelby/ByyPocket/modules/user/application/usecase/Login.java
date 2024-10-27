package com.shelby.ByyPocket.modules.user.application.usecase;

import com.shelby.ByyPocket.common.securities.JwtUtils;
import com.shelby.ByyPocket.common.securities.UserDetailsImpl;
import com.shelby.ByyPocket.modules.user.presentation.dto.request.LoginRequest;
import com.shelby.ByyPocket.modules.user.presentation.dto.response.JwtResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import static com.shelby.ByyPocket.common.Constants.LOGGED_IN_USER;

@Service
@Slf4j(topic = "Login User")
@RequiredArgsConstructor
public class Login {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public JwtResponse execute (LoginRequest data) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(data.getEmail().trim(), data.getPassword().trim()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        final UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        log.info(LOGGED_IN_USER, new Object[]{data.getEmail()});

        return JwtResponse.builder()
                .token(jwt)
                .id(userDetails.getId())
                .username(userDetails.getUsername())
                .email(userDetails.getEmail())
                .build();
    }
}
