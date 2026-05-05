package com.beki.AuthProject.io;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProfileRequest {
    @NotBlank(message = "Name should not be empty")
    private String name;
    @Email(message = "Provide a valid email address")
    @NotNull(message = "You must provide an email address")
    private String email;
    @Size(min = 6 , message = "Password must have at least 6 characters")
    private String password;
}
