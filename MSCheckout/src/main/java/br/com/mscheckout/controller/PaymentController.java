package br.com.mscheckout.controller;

import br.com.mscheckout.dto.PaymentDto;
import br.com.mscheckout.form.PaymentForm;
import br.com.mscheckout.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;


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

    @PutMapping("/payments/{id}")
    @Transactional
    public ResponseEntity<PaymentDto> atualizarPagamento(@PathVariable UUID id, @RequestBody @Valid PaymentForm paymentForm) {
        return paymentService.atualizarPagamento(id, paymentForm);
    }


    @GetMapping(value = "/payments/{id}")
    public ResponseEntity<PaymentDto> findById(@PathVariable UUID id) {
        return paymentService.findById(id);
    }


    @DeleteMapping("/v1/payments/{id}")
    public ResponseEntity<PaymentDto> deleteById(@PathVariable UUID id) {
        return paymentService.deleteById(id);
    }



}
