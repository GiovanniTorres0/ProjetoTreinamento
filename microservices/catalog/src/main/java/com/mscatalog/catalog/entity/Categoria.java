package com.mscatalog.catalog.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "categorias")
public class Categoria {

    @Id
    private Integer id;
    private String name;
    private Boolean active;


}
