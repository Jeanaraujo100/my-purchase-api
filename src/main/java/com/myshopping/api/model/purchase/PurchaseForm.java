package com.myshopping.api.model.purchase;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PurchaseForm(
        @NotBlank
        String description,
        @NotBlank
        @Pattern(regexp = "^\\d*(\\.\\d{1,2})?$")
        String price,
        @NotNull
        LocalDate date,
        @NotBlank
        String userId
) {
}
