package com.mscatalog.catalog.form;

import com.mscatalog.catalog.entity.Categoria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaForm {


    private Integer id;
    @NotBlank
    private String name;
    @NotNull
    private Boolean active;


    public Categoria converter(CategoriaForm categoriaForm) {
        Categoria categoria = new Categoria();
        categoria.setName(categoriaForm.getName());
        categoria.setActive(categoriaForm.getActive());
        return categoria;
    }


}