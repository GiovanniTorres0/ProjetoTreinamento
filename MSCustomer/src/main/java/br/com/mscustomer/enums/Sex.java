package br.com.mscustomer.enums;

import lombok.Getter;

@Getter
public enum Sex {

    MASCULINO("masculino"),
    FEMININO("feminino");


    private String sex;


    Sex(String sex) {
        this.sex = sex;
    }
}
