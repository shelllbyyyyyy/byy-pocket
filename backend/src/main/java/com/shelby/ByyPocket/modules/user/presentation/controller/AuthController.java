package com.shelby.ByyPocket.modules.user.presentation.controller;

import com.shelby.ByyPocket.modules.user.application.usecase.Login;
import com.shelby.ByyPocket.modules.user.application.usecase.ResendVerification;
import com.shelby.ByyPocket.modules.user.application.usecase.VerifyUser;
import com.shelby.ByyPocket.modules.user.presentation.dto.request.LoginRequest;
import com.shelby.ByyPocket.modules.user.presentation.dto.request.RegisterUserRequest;
import com.shelby.ByyPocket.common.responses.ApiResponse;
import com.shelby.ByyPocket.common.responses.CommandResponse;
import com.shelby.ByyPocket.modules.user.application.usecase.RegisterUser;

import com.shelby.ByyPocket.modules.user.presentation.dto.response.JwtResponse;
import com.shelby.ByyPocket.modules.user.presentation.dto.response.UserResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.shelby.ByyPocket.common.Constants.CREATED_USER;
import static com.shelby.ByyPocket.common.Constants.SUCCESS;


@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {
    private final RegisterUser registerUser;
    private final Login loginUser;
    private final VerifyUser verifyUser;
    private final ResendVerification resendVerification;

    @PostMapping("/auth/register")
    public ResponseEntity<ApiResponse<Object>> register(@Valid @RequestBody RegisterUserRequest data) {
        final CommandResponse result = registerUser.execute(data);
        final String id =  result.id();
        Map<String, Object> response = new HashMap<>();
        response.put("id", id);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        HttpStatus.CREATED.value(),
                        CREATED_USER,
                        response
                ));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<ApiResponse<Object>> login(@Valid @RequestBody LoginRequest data, HttpServletResponse response) {
        final JwtResponse user = loginUser.execute(data);
        final String token =  user.getToken();
        Map<String, Object> result = new HashMap<>();
        result.put("token", token);

        Cookie jwtCookie = new Cookie("JWT-TOKEN", user.getToken());
        jwtCookie.setHttpOnly(true);
        jwtCookie.setSecure(true);
        jwtCookie.setPath("/");
        jwtCookie.setMaxAge(60 * 60);
        response.addCookie(jwtCookie);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(
                        HttpStatus.OK.value(),
                        SUCCESS,
                        result
                ));
    }

    @GetMapping("/auth/google/callback")
    public void googleCallback(@AuthenticationPrincipal OidcUser principal, HttpServletResponse response) throws IOException {
        String redirectUrl = "http://localhost:3000";
//        OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(
//                "google", principal.getName());
//        if(client != null) {
//            Cookie jwtCookie = new Cookie("JWT-TOKEN", client.getAccessToken().getTokenValue());
//            jwtCookie.setHttpOnly(true);
//            jwtCookie.setSecure(true);
//            jwtCookie.setPath("/");
//            jwtCookie.setMaxAge(60 * 60);
//            response.addCookie(jwtCookie);
//
//            response.sendRedirect(redirectUrl);
//        } else {
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unable to retrieve access token.");
//        }

        response.sendRedirect(redirectUrl);
    }

    @GetMapping("/auth/google/revoke-token")
    public void revokeToken(Authentication authentication , HttpServletResponse response) throws IOException {
        String redirectUrl = "http://localhost:3000";

        response.sendRedirect(redirectUrl);
    }

    @PatchMapping("/auth/verify-user/{token}")
    public ResponseEntity<ApiResponse<Object>> verifyUser(@PathVariable String token) {
        final CommandResponse result = verifyUser.execute(token);
        final String id =  result.id();
        Map<String, Object> response = new HashMap<>();
        response.put("id", id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(
                        HttpStatus.OK.value(),
                        SUCCESS,
                        response
                ));
    }

    @PostMapping("/auth/resend-verification/{email}")
    public ResponseEntity<ApiResponse<Object>> resendVerification(@PathVariable String email) {
        resendVerification.execute(email);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(
                        HttpStatus.OK.value(),
                        SUCCESS,
                        null
                ));
    }

}
