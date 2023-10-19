package com.myshopping.api.model.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UserUpdate(
        @NotBlank
        String id,
        String name,
        @Pattern(regexp = "\\d{11}", message = "Phone must have 11 digit")
        String phone,
        String password
) {
}


