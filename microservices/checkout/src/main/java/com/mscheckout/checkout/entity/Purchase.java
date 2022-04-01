package com.mscheckout.checkout.entity;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "purchases")
public class Purchase {

    @Id
    @SequenceGenerator(name = "purchase_id_sequence",
            sequenceName = "purchase_id_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "purchase_id_sequence")
    private Long id;
    private String user_id;
    private String payment_id;


}
