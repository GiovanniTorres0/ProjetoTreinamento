package com.mscheckout.checkout.form;

import com.mscheckout.checkout.entity.Purchase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseForm implements Serializable {

    @NotBlank
    private String user_id;
    @NotBlank
    private String payment_id;
    @NotNull
    @OneToMany
    private List<CartForm> cart = new ArrayList<>();


    public Purchase converter(PurchaseForm purchaseForm){
        Purchase purchase = new Purchase();
        purchase.setPayment_id(purchaseForm.getPayment_id());
        purchase.setUser_id(purchaseForm.getUser_id());
        return purchase;
    }

}
