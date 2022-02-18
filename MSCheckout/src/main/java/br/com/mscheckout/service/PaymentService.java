package br.com.mscheckout.service;

import br.com.mscheckout.dto.PaymentDto;
import br.com.mscheckout.form.PaymentForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

public interface PaymentService {


    @Transactional
    ResponseEntity<PaymentDto> salvarPagamento(@RequestBody @Valid PaymentForm paymentForm);

    List<PaymentDto> findAll();

    @Transactional
    ResponseEntity<PaymentDto> atualizarPagamento(@PathVariable UUID id, @RequestBody @Valid PaymentForm paymentForm);

    ResponseEntity<PaymentDto> findById(@PathVariable UUID id);

    ResponseEntity<PaymentDto> deleteById(@PathVariable UUID id);
}
