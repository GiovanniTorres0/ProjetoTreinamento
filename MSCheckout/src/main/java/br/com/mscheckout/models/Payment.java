package br.com.mscheckout.models;

import br.com.mscheckout.enums.Card;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Payment {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private Card card;
    private Integer discount;
    private Boolean status;

    public Payment() {

    }

    public Payment(Card card, Integer discount, Boolean status) {
        this.card = card;
        this.discount = discount;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
