package com.mscatalog.catalog.dto;

import com.mscatalog.catalog.entity.Produto;
import com.mscatalog.catalog.entity.Variacao;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutosVariadosDto {

    private String name;
    private String description;
    private Integer category_ids;
    private String categoriaName;
    private List<Variacao> variacoes;



    public ProdutosVariadosDto(Produto produto) {
        this.name = produto.getName();
        this.description = produto.getDescription();
        this.category_ids = produto.getCategoria().getId();
        this.categoriaName = produto.getCategoria().getName();
        this.variacoes = produto.getVariacoes();
    }


    public static List<ProdutosVariadosDto> converterProdutos(List<Produto> produtos) {
        return produtos.stream().map(ProdutosVariadosDto::new).collect(Collectors.toList());
    }


}
