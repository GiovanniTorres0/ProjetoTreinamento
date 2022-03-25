package com.mscheckout.checkout.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartForm implements Serializable {

    @NotNull
    private Integer variant_id;
    @NotNull
    private int quantity;

}
