package com.mscatalog.catalog.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Transient;
import java.util.LinkedList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "produtos")
public class Produto {

    @Transient
    public static final String SEQUENCE_NAME = "data_sequence";


    @Id
    private Integer id;
    private String name;
    private String description;
    private Boolean active;
    @DBRef
    private Categoria categoria;
    @DBRef
    private List<Variacao> variacoes = new LinkedList<>();


}
