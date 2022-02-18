package br.com.mscatalog.dto;

import br.com.mscatalog.models.Categoria;
import br.com.mscatalog.models.Produto;

import java.util.List;
import java.util.stream.Collectors;

public class ProdutoDto {

    private String name;
    private String description;
    private Boolean active;
    private Categoria categoria;


    public ProdutoDto(){
    }

    public ProdutoDto(Produto produto){
        this.name = produto.getName();
        this.description = produto.getDescription();
        this.categoria = produto.getCategoria();
        this.active = produto.getActive();
    }

    public static List<ProdutoDto> converter(List<Produto> produtos) {
        return produtos.stream().map(ProdutoDto::new).collect(Collectors.toList());
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
