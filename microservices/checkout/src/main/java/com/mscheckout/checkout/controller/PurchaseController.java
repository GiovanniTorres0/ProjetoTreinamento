package com.mscheckout.checkout.controller;

import com.mscheckout.checkout.form.PurchaseForm;
import com.mscheckout.checkout.services.service.RabbitmqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1")
public class PurchaseController {

    @Autowired
    RabbitmqService rabbitmqService;

    @Value("${app.message}")
    private String message;

    @PostMapping(value = "/purchases")
    public String publishPurchasesDetails(@RequestBody @Valid PurchaseForm purchaseForm) throws Exception {
        rabbitmqService.send(purchaseForm);
        return message;
    }




}
