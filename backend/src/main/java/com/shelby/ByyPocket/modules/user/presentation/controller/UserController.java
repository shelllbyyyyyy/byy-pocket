package com.shelby.ByyPocket.modules.user.presentation.controller;

import com.shelby.ByyPocket.modules.user.application.usecase.DeleteByEmail;
import com.shelby.ByyPocket.modules.user.application.usecase.FindByEmail;
import com.shelby.ByyPocket.common.responses.ApiResponse;
import com.shelby.ByyPocket.modules.user.presentation.dto.response.UserResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.shelby.ByyPocket.common.Constants.SUCCESS;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final FindByEmail findByEmail;
    private final DeleteByEmail deleteByEmail;

    @GetMapping("users/{email}")
    public ResponseEntity<ApiResponse<Object>> findUserByEmail(@PathVariable String email) {
        final Optional<UserResponse> response = findByEmail.execute(email);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), SUCCESS, response));
    }

    @DeleteMapping("users/{email}")
    public ResponseEntity<ApiResponse<Object>> deleteByEmail(@PathVariable String email) {
        final boolean response = deleteByEmail.execute(email);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), SUCCESS, response));
    }

}
