package com.shelby.ByyPocket.modules.user.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

/**
 * Data Transfer Object used for authentication response
 */
@Getter
@Builder
public class JwtResponse {

    private String type;
    private String token;
    private String id;
    private String username;
    private String email;
}
