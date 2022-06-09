package com.carlos.costura.api.controller;

import com.carlos.costura.domain.model.PostItem;
import com.carlos.costura.domain.model.Purchase;
import com.carlos.costura.domain.model.SaleItem;
import com.carlos.costura.domain.model.User;
import com.carlos.costura.domain.model.dto.CartForm;
import com.carlos.costura.domain.model.dto.PurchaseOutput;
import com.carlos.costura.domain.model.dto.SaleItemForm;
import com.carlos.costura.domain.model.dto.SaleItemOutput;
import com.carlos.costura.domain.model.pk.PostItemPK;
import com.carlos.costura.domain.model.pk.SaleItemPK;
import com.carlos.costura.domain.repository.CartRepository;
import com.carlos.costura.domain.repository.PostItemRepository;
import com.carlos.costura.domain.repository.PurchaseRepository;
import com.carlos.costura.domain.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping
public class CartController {

    private CartService cartService;

    private CartRepository cartRepository;

    private PurchaseRepository purchaseRepository;

    private PostItemRepository postItemRepository;

    @PostMapping("/buy")
    public ResponseEntity<?> buy(@RequestBody CartForm cartForm, UriComponentsBuilder uriComponentsBuilder){
        Purchase purchase = cartService.save(cartForm);
        UriComponents uriComponents = uriComponentsBuilder.path("/purchases/{id}").buildAndExpand(purchase.getId());
        var location = uriComponents.toUri();
        return ResponseEntity.created(location).body(getPurchaseOutput(purchase));
    }

    @GetMapping("/purchases/{purchaseId}")
    public ResponseEntity<?> getPurchase(@PathVariable Long purchaseId){
        User user = User.isAuthenticatedReturnUser();
        Purchase purchase = cartRepository.findById(purchaseId).get();
        PurchaseOutput purchaseOutput = getPurchaseOutput(purchase);

        return ResponseEntity.ok(purchaseOutput);
    }

    private PurchaseOutput getPurchaseOutput(Purchase purchase) {
        PurchaseOutput purchaseOutput = new PurchaseOutput();
        purchaseOutput.setId(purchase.getId());
        purchaseOutput.setData(purchase.getDate());
        purchaseOutput.setTotal(purchase.getTotal());
        for (SaleItem i :
                purchase.getItems()) {
            SaleItemForm saleItemForm = new SaleItemForm();
            saleItemForm.setItem(i.getSaleItemPK().getItem());
            saleItemForm.setPostId(i.getSaleItemPK().getPost().getId());
            PostItem postItem = postItemRepository.findByPostItemPK_ItemAndPostItemPK_Post(i.getSaleItemPK().getItem(),i.getSaleItemPK().getPost());
            saleItemForm.setDescription(postItem.getDescription());
            saleItemForm.setPrice(postItem.getPrice());
            purchaseOutput.getItems().add(saleItemForm);
        }
        return purchaseOutput;
    }

    @GetMapping("/purchases")
    public ResponseEntity<?> getPurchases(){
        User user = User.isAuthenticatedReturnUser();
        List<Purchase> purchasesList = cartRepository.findAllByUser(user.getId());
        List<PurchaseOutput> purchaseOutputList = purchasesList.stream().map(p -> getPurchaseOutput(p)).collect(Collectors.toList());
        return ResponseEntity.ok(purchaseOutputList);
    }

    @GetMapping("/sales")
    public ResponseEntity<?> getSales(){
        User user = User.isAuthenticatedReturnUser();
        List<SaleItemOutput> saleList = cartRepository.findAllSalesByUser(user.getId());
        return ResponseEntity.ok((saleList));
    }

}
