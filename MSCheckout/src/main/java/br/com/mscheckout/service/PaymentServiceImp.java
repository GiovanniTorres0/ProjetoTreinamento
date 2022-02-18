package br.com.mscheckout.service;

import br.com.mscheckout.dto.PaymentDto;
import br.com.mscheckout.form.PaymentForm;
import br.com.mscheckout.models.Payment;
import br.com.mscheckout.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PaymentServiceImp implements PaymentService {

    @Autowired
    PaymentRepository paymentRepository;

    @Override
    @Transactional
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
    public ResponseEntity<PaymentDto> atualizarPagamento(@PathVariable UUID id, @RequestBody @Valid PaymentForm paymentForm) {
            Optional<Payment> optional = paymentRepository.findById(id);
            if(optional.isPresent()){
                Payment payment = paymentForm.converter();
                paymentRepository.save(payment);
                return ResponseEntity.ok(new PaymentDto(payment));
            }
        return ResponseEntity.notFound().build();
   }


    @Override
    public ResponseEntity<PaymentDto> findById(@PathVariable UUID id) {
        Optional<Payment> optional = paymentRepository.findById(id);
        if(optional.isPresent()) {
            return ResponseEntity.ok(new PaymentDto(optional.get()));
        }
        return ResponseEntity.notFound().build();
    }


    @Override
    public ResponseEntity<PaymentDto> deleteById (@PathVariable UUID id) {
        Optional<Payment> optional = paymentRepository.findById(id);
        if(optional.isPresent()){
            paymentRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }







}
