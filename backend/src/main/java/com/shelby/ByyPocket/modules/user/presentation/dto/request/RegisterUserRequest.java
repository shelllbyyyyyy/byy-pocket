package com.shelby.ByyPocket.modules.user.presentation.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserRequest {
    @Size(min = 3, max = 50, message = "{username.size}")
    @NotBlank(message = "{username.notblank}")
    private String username;

    @Email(message = "email.acceptable")
    @Size(min = 3, max = 50, message = "{email.size}")
    @NotBlank(message = "{email.notblank}")
    private String email;

    @Size(min = 8, max = 255, message = "{password.size}")
    @NotBlank(message = "{password.notblank}")
    private String password;
}
