package com.myshopping.api.controller;

import com.myshopping.api.model.purchase.PurchaseForm;
import com.myshopping.api.model.purchase.PurchaseUpdate;
import com.myshopping.api.model.purchase.PurchaseView;
import com.myshopping.api.service.PurchaseService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/purchase")
public class PurchasesController {
    @Autowired
    private PurchaseService purchaseService;

    @PostMapping
    @Transactional
    public ResponseEntity addPurchase(
            @RequestBody @Valid PurchaseForm purchaseForm,
            UriComponentsBuilder uriBuilder
    ){
        var purchaseView = purchaseService.addPurchase(purchaseForm);
        var uri = uriBuilder.path("purchase/{id}").buildAndExpand(purchaseView.id()).toUri();
        return ResponseEntity.created(uri).body(purchaseView);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity getPurchases(@PathVariable("id") String userId){
        var purchases = purchaseService.getPurchases(userId);
        return ResponseEntity.ok(purchases);
    }

    @PutMapping
    @Transactional
    public ResponseEntity putPurchase(@RequestBody @Valid PurchaseUpdate purchaseUpdate){
        var purchaseUpdated = purchaseService.update(purchaseUpdate);
        return ResponseEntity.ok(purchaseUpdated);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletePurchase(@PathVariable("id") String id){
        purchaseService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detailPurchase(@PathVariable("id") String id){
        PurchaseView purchaseView = purchaseService.detail(id);
        return ResponseEntity.ok(purchaseView);
    }
}
