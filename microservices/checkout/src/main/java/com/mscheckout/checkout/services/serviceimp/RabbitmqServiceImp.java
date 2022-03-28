package com.mscheckout.checkout.services.serviceimp;

import com.mscheckout.checkout.entity.Payment;
import com.mscheckout.checkout.form.PurchaseForm;
import com.mscheckout.checkout.repository.PaymentRepository;
import com.mscheckout.checkout.services.service.RabbitmqService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.Optional;

@Service
public class RabbitmqServiceImp implements RabbitmqService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    PaymentRepository paymentRepository;


    @Value("${spring.rabbitmq.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.routingkey}")
    private String routingkey;


    @Override
    public String send(@RequestBody @Valid PurchaseForm purchaseForm) {
        Integer idP = Integer.valueOf(purchaseForm.getPayment_id());
        String worth;
        Optional<Payment> optionalPayment = paymentRepository.findById(idP);
        if (optionalPayment.isPresent()) {
            rabbitTemplate.convertAndSend(exchange, routingkey, purchaseForm);
            rabbitTemplate.convertAndSend("history.exchange", "history.routingkey", purchaseForm);
            return "MENSAGEM ENVIADA";
        } else {
            return "MENSAGEM NÃO ENVIADA";
        }

    }


}



