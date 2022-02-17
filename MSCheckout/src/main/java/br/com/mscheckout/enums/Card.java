package br.com.mscheckout.enums;


public enum Card {

    CREDIT("credit"),
    DEBIT("debit");


    private String card;

    Card(String card) {
        this.card = card;
    }


}
