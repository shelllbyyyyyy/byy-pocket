package com.shelby.ByyPocket.modules.user.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class UserResponse {
    private String id;
    private String username;
    private String email;
    @JsonIgnore
    private String password;
    private boolean isVerified;
    private String provider;
}


