package com.mshistory.history.dto;

import com.mscatalog.catalog.dto.ProdutosVariadosDto;
import com.mscheckout.checkout.dto.PaymentDto;
import com.mscustomer.customer.dto.UsuarioDto;
import com.mshistory.history.entity.History;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoryDto {

    private UsuarioDto user;
    private PaymentDto payment;
    private List<ProdutosVariadosDto> produtosVariadosDto;
    private double total;
    private String data;


    public HistoryDto(History history){
        this.user = history.getUser();
        this.payment = history.getPayment();
        this.produtosVariadosDto = history.getProdutosVariadosDto();
        this.total = history.getTotal();
        this.data = history.getData();
    }


}
