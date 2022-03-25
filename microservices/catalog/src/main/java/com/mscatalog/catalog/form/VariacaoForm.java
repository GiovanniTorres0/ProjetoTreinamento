package com.mscatalog.catalog.form;

import com.mscatalog.catalog.entity.Produto;
import com.mscatalog.catalog.entity.Variacao;
import com.mscatalog.catalog.enums.Size;
import com.mscatalog.catalog.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class VariacaoForm {


    private Integer id;
    @NotBlank
    private String color;
    @NotBlank
    private Size size;
    @NotBlank
    private Double price;
    @NotBlank
    private int quantity;
    @NotBlank
    private Integer product_id;


    public List<Variacao> converter(List<VariacaoForm> listForm) {
        List<Variacao> variacoes = listForm.stream().map(var -> new Variacao(
                var.getId(), var.getColor(), var.getSize(), var.getPrice(), var.getQuantity(), var.getProduct_id())).collect(Collectors.toList());
        return variacoes;
    }


    public Produto retorna(List<VariacaoForm> list, ProdutoRepository produtoRepository) {
        Produto produto = new Produto();
        for (int i = 0; i < list.size(); i++) {
            Optional<Produto> optional = produtoRepository.findById(list.get(i).getProduct_id());
            if (optional.isPresent()) {
                produto = optional.get();
            }
        }
        return produto;
    }


}

