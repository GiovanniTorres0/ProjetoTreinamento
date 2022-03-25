package com.mscatalog.catalog.dto;

import com.mscatalog.catalog.entity.Categoria;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaDto {

    private String name;



    public CategoriaDto(Categoria categories){
        this.name = categories.getName();
    }

    public static List<CategoriaDto> converter(List<Categoria> categorias){
        return categorias.stream().map(CategoriaDto::new).collect(Collectors.toList());
    }


}
