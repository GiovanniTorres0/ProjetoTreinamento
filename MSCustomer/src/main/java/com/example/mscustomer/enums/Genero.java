package com.example.mscustomer.enums;

public enum Genero {

    MASCULINO("masculino"),
    FEMININO("feminino");


    private String genero;

    public String getGenero() {
        return genero;
    }

    Genero(String genero) {
        this.genero = genero;
    }

}
