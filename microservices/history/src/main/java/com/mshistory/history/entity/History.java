package com.mshistory.history.entity;


import com.mscatalog.catalog.dto.ProdutosVariadosDto;
import com.mscheckout.checkout.dto.PaymentDto;
import com.mscustomer.customer.dto.UsuarioDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "history")
public class History {

    @Transient
    public static final String SEQUENCE_NAME = "history_sequence";

    @Id
    private Integer id;
    private UsuarioDto user;
    private PaymentDto payment;
    private List<ProdutosVariadosDto> produtosVariadosDto;
    private String data;
    private double total;

}
