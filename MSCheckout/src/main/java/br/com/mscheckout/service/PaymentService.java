package br.com.mscheckout.service;

import br.com.mscheckout.dto.PaymentDto;
import br.com.mscheckout.form.PaymentForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

public interface PaymentService {

    ResponseEntity<PaymentDto> salvarPagamento(@RequestBody @Valid PaymentForm paymentForm);

    List<PaymentDto> findAll();


}
