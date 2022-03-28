package com.mscheckout.checkout.entity;

import com.mscheckout.checkout.enums.Card;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "payments")
public class Payment {


    @Id
    @SequenceGenerator(name = "payment_id_sequence",
            sequenceName = "payment_id_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_id_sequence")
    private Integer id;
    private Card card;
    private int discount;
    private Boolean status;


}
