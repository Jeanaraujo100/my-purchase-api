package com.myshopping.api.model.purchase;

import com.myshopping.api.model.user.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Table(name = "purchase")
@Entity(name = "purchase")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Purchase {
    @Id
    private String id;
    private String description;
    private BigDecimal price;
    @Column(name = "date_purchase")
    private LocalDate datePurchase;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;

    public void update(PurchaseUpdate purchaseUpdate) {
        if (purchaseUpdate.description() != null){
            this.description = purchaseUpdate.description();
        }
        if (purchaseUpdate.price() != null){
            this.price = new BigDecimal(purchaseUpdate.price());
        }
        if (purchaseUpdate.date() != null){
            this.datePurchase = purchaseUpdate.date();
        }
    }
}
