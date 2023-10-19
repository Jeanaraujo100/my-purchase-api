package com.myshopping.api.model.purchase.mapper;

import com.myshopping.api.model.purchase.Purchase;
import com.myshopping.api.model.purchase.PurchaseForm;
import com.myshopping.api.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
public class PurchaseMapper implements Mapper<PurchaseForm, Purchase> {
    @Autowired
    private UsersRepository usersRepository;
    @Override
    public Purchase map(PurchaseForm purchaseForm) {
        return new Purchase(
                UUID.randomUUID().toString(),
                purchaseForm.description(),
                new BigDecimal(purchaseForm.price()),
                purchaseForm.date(),
                usersRepository.getReferenceById(purchaseForm.userId())
        );
    }
}
