package com.mscatalog.catalog.form;

import com.mscatalog.catalog.entity.Variacao;
import com.mscatalog.catalog.enums.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;


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


    public List<Variacao> converterLista(List<VariacaoForm> listForm) {
        List<Variacao> variacaos = new ArrayList<>(10);
        Variacao variacao[] = new Variacao[listForm.size()];
        for (int i = 0; i < listForm.size(); i++) {
            variacao[i] = new Variacao(listForm.get(i).getId(), listForm.get(i).getColor(), listForm.get(i).getSize(), listForm.get(i).getPrice(), listForm.get(i).getQuantity(), listForm.get(i).getProduct_id());
        }
        variacaos.addAll(List.of(variacao));
        return variacaos;
    }
}

