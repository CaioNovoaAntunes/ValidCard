package com.br.cardvalidator.dto;

import com.br.cardvalidator.enumerador.CreditorDebit;
import com.br.cardvalidator.model.Card;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class CardRequest {
    @NotBlank
    @NotNull
    private String numeroCartao;
    @JsonProperty
    @Enumerated(EnumType.STRING)
    private CreditorDebit creditorDebit;



    public Card toModel() {
        return new Card(this.numeroCartao, this.creditorDebit);
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

}
