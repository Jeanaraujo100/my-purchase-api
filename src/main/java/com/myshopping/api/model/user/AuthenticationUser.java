package com.myshopping.api.model.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AuthenticationUser(
        @NotBlank
        String phone,
        @NotBlank
        @Pattern(regexp = "\\d{11}")
        String password
) {
}
