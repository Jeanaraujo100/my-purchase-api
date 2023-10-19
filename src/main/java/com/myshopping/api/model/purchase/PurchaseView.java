package com.myshopping.api.model.purchase;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PurchaseView(
        String id,
        String description,
        BigDecimal price,
        LocalDate date,
        String userId
) {
    public PurchaseView(Purchase purchase) {
        this(
                purchase.getId(),
                purchase.getDescription(),
                purchase.getPrice(),
                purchase.getDatePurchase(),
                purchase.getUser().getId()
        );
    }
}
