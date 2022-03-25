package com.mscheckout.checkout.services.serviceimp;

import com.mscheckout.checkout.dto.PaymentDto;
import com.mscheckout.checkout.entity.Payment;
import com.mscheckout.checkout.form.PaymentForm;
import com.mscheckout.checkout.repository.PaymentRepository;
import com.mscheckout.checkout.services.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImp implements PaymentService {

    @Autowired
    PaymentRepository paymentRepository;

    @Override
    @Transactional
    public ResponseEntity<PaymentDto> salvarPagamento(@RequestBody @Valid @RequestParam PaymentForm paymentForm) {
        Payment payment = paymentForm.converter(paymentForm);
        if (payment.getStatus()) {
            if (payment != null) {
                return new ResponseEntity<PaymentDto>(new PaymentDto(paymentRepository.save(payment)), HttpStatus.CREATED);
            }
        }
        return new ResponseEntity<PaymentDto>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public List<PaymentDto> findAll() {
        try {
            List<Payment> payments = paymentRepository.findAll();
            if (payments != null) {
                return PaymentDto.converter(payments);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @Transactional
    public ResponseEntity<PaymentDto> atualizarPagamento(@PathVariable Integer id, @RequestBody @Valid PaymentForm paymentForm) {
        Optional<Payment> optional = paymentRepository.findById(id);
        if (optional.isPresent()) {
            Payment payment = paymentForm.converter(paymentForm);
            payment.setId(id);
            return new ResponseEntity<PaymentDto>(new PaymentDto(paymentRepository.save(payment)), HttpStatus.ACCEPTED);
        }
        return ResponseEntity.notFound().build();
    }


    @Override
    public ResponseEntity<PaymentDto> findById(@PathVariable Integer id) {
        Optional<Payment> optional = paymentRepository.findById(id);
        if (optional.isPresent()) {
            return ResponseEntity.ok(new PaymentDto(optional.get()));
        }
        return ResponseEntity.notFound().build();
    }


    @Override
    public ResponseEntity<PaymentDto> deleteById(@PathVariable Integer id) {
        Optional<Payment> optional = paymentRepository.findById(id);
        if (optional.isPresent()) {
            paymentRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

    @Override
    public PaymentDto retorna(Integer id){
        Optional<Payment> payment = paymentRepository.findById(id);
        if(payment.isPresent()){
            return new PaymentDto(payment.get());
        }
        throw new EntityNotFoundException();
    }

}
