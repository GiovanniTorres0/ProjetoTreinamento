package com.mscheckout.checkout.dto;

import com.mscheckout.checkout.entity.Payment;
import com.mscheckout.checkout.enums.Card;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class PaymentDto {


    private Card card;
    private int discount;
    private Boolean status;


    public PaymentDto(Payment payment) {
        this.card = payment.getCard();
        this.discount = payment.getDiscount();
        this.status = payment.getStatus();
    }

    public static List<PaymentDto> converter(List<Payment> payments) {
        return payments.stream().map(PaymentDto::new).collect(Collectors.toList());


    }
}