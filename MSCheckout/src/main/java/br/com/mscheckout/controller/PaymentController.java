package br.com.mscheckout.controller;

import br.com.mscheckout.dto.PaymentDto;
import br.com.mscheckout.form.PaymentForm;
import br.com.mscheckout.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/v1")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @GetMapping(value = "/payments")
    public List<PaymentDto> getAll() {
        return paymentService.findAll();
    }

    @PostMapping(value = "/payments")
    public ResponseEntity<PaymentDto> salvarPagamento(@RequestBody @Valid PaymentForm paymentForm) {
        return paymentService.salvarPagamento(paymentForm);
    }


}
