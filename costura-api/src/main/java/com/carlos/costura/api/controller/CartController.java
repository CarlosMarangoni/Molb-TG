package com.carlos.costura.api.controller;

import com.carlos.costura.domain.model.Purchase;
import com.carlos.costura.domain.model.User;
import com.carlos.costura.domain.model.dto.CartForm;
import com.carlos.costura.domain.model.dto.PurchaseOutput;
import com.carlos.costura.domain.repository.CartRepository;
import com.carlos.costura.domain.repository.PurchaseRepository;
import com.carlos.costura.domain.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping
public class CartController {

    private CartService cartService;

    private CartRepository cartRepository;

    private PurchaseRepository purchaseRepository;

    @PostMapping("/buy")
    public ResponseEntity<?> buy(@RequestBody CartForm cartForm){
        boolean saved = cartService.save(cartForm);
        return ResponseEntity.ok(saved);
    }

//    @GetMapping("/purchases")
//    public ResponseEntity<?> getPurchases(){
//        User user = User.isAuthenticatedReturnUser();
//        List<Purchase> purchasesList = cartRepository.findAllByUser(user.getId());
//        return ResponseEntity.ok(purchasesList.stream().map(PurchaseOutput::toOutput));
//    }

//    @GetMapping("/sales")
//    public ResponseEntity<?> getSales(){
//        User user = User.isAuthenticatedReturnUser();
//        List<Purchase> saleList = saleRepository.findAllByUser(user.getId());
//        return ResponseEntity.ok(saleList.stream().map(SaleOutput::toOutput));
//    }

}
