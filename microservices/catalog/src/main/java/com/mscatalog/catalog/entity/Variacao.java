package com.mscatalog.catalog.entity;

import com.mscatalog.catalog.enums.Size;
import com.mscatalog.catalog.services.sequence.SequenceGeneratorService;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "variacoes")
public class Variacao {


    @Id
    private Integer id;
    private String color;
    @Enumerated(value = EnumType.STRING)
    private Size size;
    private Double price;
    private int quantity;
    private Integer product_id;


}
