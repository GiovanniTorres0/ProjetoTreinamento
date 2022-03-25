package com.mscheckout.checkout.services.service;


import com.mscheckout.checkout.dto.PaymentDto;
import com.mscheckout.checkout.form.PaymentForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

public interface PaymentService {


    @Transactional
    ResponseEntity<PaymentDto> salvarPagamento(@RequestBody @Valid PaymentForm paymentForm);

    List<PaymentDto> findAll();

    @Transactional
    ResponseEntity<PaymentDto> atualizarPagamento(@PathVariable Integer id, @RequestBody @Valid PaymentForm paymentForm);

    ResponseEntity<PaymentDto> findById(@PathVariable Integer id);

    ResponseEntity<PaymentDto> deleteById(@PathVariable Integer id);

    PaymentDto retorna(Integer id);
}
