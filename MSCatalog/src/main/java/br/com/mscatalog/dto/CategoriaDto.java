package br.com.mscatalog.dto;

import br.com.mscatalog.models.Categoria;
import br.com.mscatalog.models.Produto;

import java.util.List;
import java.util.stream.Collectors;

public class CategoriaDto {


    private String name;
    private Boolean active;


    public CategoriaDto(Categoria categoria){
        this.name = categoria.getName();
        this.active = categoria.getActive();
    }

    public static List<CategoriaDto> converter(List<Categoria> categorias) {
        return categorias.stream().map(CategoriaDto::new).collect(Collectors.toList());
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
