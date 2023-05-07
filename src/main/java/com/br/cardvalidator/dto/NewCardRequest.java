package com.br.cardvalidator.dto;

import com.br.cardvalidator.enumerador.TypeCard;
import com.br.cardvalidator.model.Card;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class NewCardRequest {
    @NotBlank
    private String numberCard;
    @NotBlank
    private String titularName;

    @JsonProperty
    @Enumerated(EnumType.STRING)
    @NotNull(message = "O campo 'type' não pode ser nulo")
    private TypeCard typeCard;

    public NewCardRequest(String numberCard, String titularName, TypeCard typeCard) {
        this.numberCard = numberCard;
        this.titularName = titularName;
        this.typeCard = typeCard;
    }

    public Card toModel() {
        return new Card(this.titularName,this.numberCard, this.typeCard);
    }

    public String getNumberCard() {
        return numberCard;
    }

    public String getNumeroCartao() {
      return  this.getNumberCard();
    }
}
