package br.com.mscatalog.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Getter
@Setter
@Document(collection = "produtos")
public class Produto {
    private static final long serialVersionUID = 1L;
    @Id
    private Long id;
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
}
