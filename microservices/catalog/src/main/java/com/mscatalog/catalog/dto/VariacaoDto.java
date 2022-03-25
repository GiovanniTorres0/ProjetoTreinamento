package com.mscatalog.catalog.dto;

import com.mscatalog.catalog.entity.Variacao;
import com.mscatalog.catalog.enums.Size;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
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

    public static List<VariacaoDto> converter(List<Variacao> variacoes) {
        return variacoes.stream().map(VariacaoDto::new).collect(Collectors.toList());
    }


}
