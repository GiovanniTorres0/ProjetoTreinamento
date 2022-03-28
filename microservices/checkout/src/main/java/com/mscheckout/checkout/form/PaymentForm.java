package com.mscheckout.checkout.form;

import com.mscheckout.checkout.entity.Payment;
import com.mscheckout.checkout.enums.Card;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentForm implements Serializable {

    @NotNull
    private Card card;
    @NotNull
    private int discount;
    @NotNull
    private Boolean status;


    public Payment converter(PaymentForm paymentForm){
        Payment payment = new Payment();
        payment.setCard(paymentForm.getCard());
        payment.setDiscount(paymentForm.getDiscount());
        payment.setStatus(paymentForm.getStatus());
        return payment;
    }


}
