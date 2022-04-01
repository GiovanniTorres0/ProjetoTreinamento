package com.msbffshop.bffshop.controller;

import com.msbffshop.bffshop.services.endpointservice.CheckoutMicroService;
import com.mscheckout.checkout.controller.PaymentController;
import com.mscheckout.checkout.dto.PaymentDto;
import com.mscheckout.checkout.form.PurchaseForm;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1")
public class BffCheckoutController {

    @Autowired
    CheckoutMicroService checkoutMicroService;

    @ApiOperation(value = "Busca todos os pagamentos", notes = "Busca todos os dados de pagamentos", response = PaymentController.class)
    @ApiResponses({@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 404, message = "Not Found")})
    @GetMapping("/payments")
    public List<PaymentDto> buscaTodosOsPagamentos() {
        return checkoutMicroService.buscaTodosOsPagamentos();
    }


    @ApiOperation(value = "Envia os detalhes da compra para catalogo e history", notes = "Envia modelo PurchaseForm", response = BffCheckoutController.class)
    @ApiResponses({@ApiResponse(code = 200, message = "OK")})
    @PostMapping("/purchases")
    public String publishPurchasesDetails(@RequestBody @Valid PurchaseForm purchaseForm) {
        return checkoutMicroService.RegistraPagamento(purchaseForm);
    }

}
