package com.mscatalog.catalog.dto;

import com.mscatalog.catalog.entity.Produto;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDto {


    private String name;
    private String description;
    private Integer category_ids;
    private String categoriaName;


    public ProdutoDto(Produto produto) {
        this.name = produto.getName();
        this.description = produto.getDescription();
        this.category_ids = produto.getCategoria().getId();
        this.categoriaName = produto.getCategoria().getName();
    }


}
