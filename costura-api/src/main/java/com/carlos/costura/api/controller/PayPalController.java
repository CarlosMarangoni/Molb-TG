package com.carlos.costura.api.controller;

import com.carlos.costura.domain.configuration.PaypalConfig;
import com.carlos.costura.domain.service.PaypalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping(value = "/paypal")
public class PayPalController {

    @Autowired
    private PaypalService paypalService;


}