package com.myshopping.api.service;

import com.myshopping.api.model.purchase.*;
import com.myshopping.api.model.purchase.mapper.PurchaseMapper;
import com.myshopping.api.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseService {
    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private PurchaseMapper purchaseMapper;

    public PurchaseView addPurchase(PurchaseForm purchaseForm){
        var purchase = purchaseMapper.map(purchaseForm);
        purchaseRepository.save(purchase);
        return new PurchaseView(purchase);
    }

    public List<PurchaseView> getPurchases(String userId) {
        var purchases = purchaseRepository.findAllByUserId(userId);
        return purchases.stream().map(PurchaseView::new).toList();
    }

    public PurchaseView update(PurchaseUpdate purchaseUpdate) {
        var purchase = purchaseRepository.getReferenceById(purchaseUpdate.id());
        purchase.update(purchaseUpdate);
        return new PurchaseView(purchase);
    }

    public void delete(String id) {
        purchaseRepository.deleteById(id);
    }

    public PurchaseView detail(String id) {
        var purchase = purchaseRepository.getReferenceById(id);
        return new PurchaseView(purchase);
    }
}
