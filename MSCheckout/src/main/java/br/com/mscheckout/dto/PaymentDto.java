package br.com.mscheckout.dto;

import br.com.mscheckout.enums.Card;
import br.com.mscheckout.models.Payment;

import java.util.List;
import java.util.stream.Collectors;


public class PaymentDto {


    private Card card;
    private Integer discount;
    private Boolean status;


    public PaymentDto(Payment payment) {
        this.card = payment.getCard();
        this.discount = payment.getDiscount();
        this.status = payment.getStatus();
    }


    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public static List<PaymentDto> converter(List<Payment> payments) {
        return payments.stream().map(PaymentDto::new).collect(Collectors.toList());


    }
}