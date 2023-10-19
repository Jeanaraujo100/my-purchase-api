package com.myshopping.api.model.purchase;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record PurchaseUpdate(
        @NotBlank
        String id,
        String description,
        @Pattern(regexp = "^\\d*(\\.\\d{1,2})?$", message = "Default xxxx.xx")
        String price,
        LocalDate date
) {
}
