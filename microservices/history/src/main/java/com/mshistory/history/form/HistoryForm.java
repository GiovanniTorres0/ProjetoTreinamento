package com.mshistory.history.form;

import com.mscatalog.catalog.dto.ProdutosVariadosDto;
import com.mscheckout.checkout.dto.PaymentDto;
import com.mscustomer.customer.dto.UsuarioDto;
import com.mshistory.history.entity.History;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoryForm {

    @NotBlank
    private UsuarioDto user;
    @NotBlank
    private PaymentDto payment;
    @NotBlank
    private List<ProdutosVariadosDto> produtosVariadosDto;
    @NotBlank
    private String data;
    @NotBlank
    private double total;



    public History converter(HistoryForm historyForm){
        History history = new History();
        history.setData(historyForm.getData());
        history.setPayment(historyForm.getPayment());
        history.setTotal(historyForm.getTotal());
        history.setUser(historyForm.getUser());
        history.setProdutosVariadosDto(historyForm.getProdutosVariadosDto());
        return history;
    }

}
