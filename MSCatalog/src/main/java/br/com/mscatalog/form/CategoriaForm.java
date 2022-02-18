package br.com.mscatalog.form;

import br.com.mscatalog.models.Categoria;

import javax.validation.constraints.NotBlank;

public class CategoriaForm {

    @NotBlank
    private String name;


    public CategoriaForm() {

    }

    public Categoria converter() {
        return new Categoria(this.name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
