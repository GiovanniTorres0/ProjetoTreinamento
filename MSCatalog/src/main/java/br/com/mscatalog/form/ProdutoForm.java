package br.com.mscatalog.form;

import br.com.mscatalog.models.Categoria;
import br.com.mscatalog.models.Produto;

import javax.validation.constraints.NotBlank;

public class ProdutoForm {

    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotBlank
    private Boolean active;
    @NotBlank
    private Categoria categoria;


    public ProdutoForm() {
    }


    public Produto converter() {
        return new Produto(this.name, this.description, this.active, this.categoria);
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
