package com.carlos.costura.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DashboardController {

    @GetMapping("/users/{userId}/sales/details")
    public void getSalesDetails(){

    }
}
