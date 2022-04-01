package com.mscheckout.checkout.controller;

import com.mscheckout.checkout.form.PurchaseForm;
import com.mscheckout.checkout.services.service.RabbitmqService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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


    @ApiOperation(value = "Envia os detalhes da compra para catalogo e history", notes = "Envia modelo PurchaseForm", response = PaymentController.class)
    @ApiResponses({@ApiResponse(code = 200, message = "OK")})
    @PostMapping(value = "/purchases")
    public String publishPurchasesDetails(@RequestBody @Valid PurchaseForm purchaseForm) throws Exception {
        rabbitmqService.send(purchaseForm);
        return message;
    }




}
