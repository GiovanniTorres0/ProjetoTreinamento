package br.com.mscheckout.service;

import br.com.mscheckout.dto.PaymentDto;
import br.com.mscheckout.form.PaymentForm;
import br.com.mscheckout.models.Payment;
import br.com.mscheckout.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
@Service
public class PaymentServiceImp implements PaymentService {

    @Autowired
    PaymentRepository paymentRepository;

    @Override
    public ResponseEntity<PaymentDto> salvarPagamento(@RequestBody @Valid PaymentForm paymentForm) {
        Payment payment = paymentForm.converter();
        if (payment != null) {
            payment = paymentRepository.save(payment);
            return new ResponseEntity<PaymentDto>(new PaymentDto(payment), HttpStatus.CREATED);
        }
        return new ResponseEntity<PaymentDto>(HttpStatus.BAD_REQUEST);
    }
    @Override
    public List<PaymentDto> findAll() {
        List<Payment> payments = paymentRepository.findAll();
        return PaymentDto.converter(payments);

    }


}
