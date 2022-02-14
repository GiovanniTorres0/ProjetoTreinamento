package br.com.mscatalog.models;

import br.com.mscatalog.enums.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Variacao {

    private String color;
    private Size size;
    private Double price;
    private Integer quantity;
    private Long productId;

}
