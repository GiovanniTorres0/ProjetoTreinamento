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
    private List<PaymentDto> payments;
    private List<ProdutosVariadosDto> produtosVariadosDto;
    private double total;
    private String data;

    public History converte(HistoryDto historyDto){
        History history = new History();
        history.setUser(historyDto.getUser());
        history.setData(historyDto.getData());
        history.setTotal(historyDto.getTotal());
        history.setProdutosVariadosDto(historyDto.getProdutosVariadosDto());
        history.setPayment(historyDto.getPayments());
        return history;

    }
}
