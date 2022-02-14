package br.com.mscheckout.enums;

import lombok.Getter;

@Getter
public enum Card {

    CREDIT("credit"),
    DEBIT("debit");


    private String card;

    Card(String card) {
        this.card = card;
    }


}
