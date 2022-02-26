package com.carlos.costura.api.controller;

import com.carlos.costura.domain.model.dto.CartForm;
import com.carlos.costura.domain.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping
public class CartController {

    private CartService cartService;

    @PostMapping("/buy")
    public ResponseEntity<?> buy(@RequestBody CartForm cartForm){

        cartService.save(cartForm);

        return ResponseEntity.ok().build();
    }

}
