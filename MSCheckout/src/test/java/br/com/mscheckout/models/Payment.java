package br.com.mscheckout.models;

import br.com.mscheckout.enums.Card;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Payment {

    private Card card;
    private Integer discount;
    private Boolean status;



}
