package com.msbffshop.bffshop.services.endpointservice;

import com.mscheckout.checkout.dto.PaymentDto;
import com.mscheckout.checkout.form.PurchaseForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "checkout", url = "http://localhost:8083/v1")
public interface CheckoutMicroService {

    @RequestMapping(method = RequestMethod.GET, value = "/payments")
    List<PaymentDto> buscaTodosOsPagamentos();

    @RequestMapping(method = RequestMethod.POST, value = "/purchases")
    String RegistraPagamento(@RequestBody @Valid PurchaseForm purchaseForm);

}
