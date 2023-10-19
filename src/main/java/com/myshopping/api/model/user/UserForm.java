package com.myshopping.api.model.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UserForm(
        @NotBlank(message = "The name field not must to be null")
        String name,
        @NotBlank(message = "The phone field not must to be null")
        @Pattern(regexp = "\\d{11}", message = "Phone must have 11 digit")
        String phone,
        @NotBlank(message = "The password field not must to be null")
        String password
) {
}
