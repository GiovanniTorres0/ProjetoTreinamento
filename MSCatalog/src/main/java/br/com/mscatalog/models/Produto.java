package br.com.mscatalog.models;


import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.UUID;


@Document(collection = "produtos")
public class Produto {

    @Id
    private UUID id;
    private String name;
    private String description;
    private Boolean active;
    private Categoria categoria;


    public Produto() {

    }

    public Produto(String name, String description, Boolean active, Categoria categoria) {
        this.name = name;
        this.description = description;
        this.active = active;
        this.categoria = categoria;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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
