package com.myshopping.api.repository;

import com.myshopping.api.model.purchase.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, String> {
    List<Purchase> findAllByUserId(String userId);
}
