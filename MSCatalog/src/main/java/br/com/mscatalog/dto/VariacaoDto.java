package br.com.mscatalog.dto;

import br.com.mscatalog.enums.Size;
import br.com.mscatalog.models.Variacao;

import java.util.List;
import java.util.stream.Collectors;

public class VariacaoDto {


    private String color;
    private Size size;
    private Double price;
    private int quantity;


    public VariacaoDto(Variacao variacao) {
        this.color = variacao.getColor();
        this.size = variacao.getSize();
        this.price = variacao.getPrice();
        this.quantity = variacao.getQuantity();
    }


}
