package br.com.mscatalog.form;

import br.com.mscatalog.enums.Size;
import br.com.mscatalog.models.Variacao;

import javax.validation.constraints.NotBlank;

public class VariacaoForm {

    @NotBlank
    private String color;
    @NotBlank
    private Size size;
    @NotBlank
    private Double price;
    @NotBlank
    private int quantity;


    public VariacaoForm(){

    }

    public Variacao converter(){
        return new Variacao(this.color, this.size, this.price, this.quantity);
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
