package br.com.mscheckout.form;

import br.com.mscheckout.enums.Card;
import br.com.mscheckout.models.Payment;

import javax.validation.constraints.NotBlank;

public class PaymentForm {

    @NotBlank
    private Card card;
    @NotBlank
    private Integer discount;
    @NotBlank
    private Boolean status;


    public PaymentForm(){
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

    public Payment converter() {
        return new Payment(this.card, this.discount, this.status);
    }



}
