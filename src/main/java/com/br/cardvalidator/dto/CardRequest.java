package com.br.cardvalidator.dto;

import com.br.cardvalidator.enumerador.CreditorDebit;
import com.br.cardvalidator.model.Card;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class CardRequest {
    @NotBlank
    @NotNull
    private String numeroCartao;
    @NotBlank
    @NotEmpty
    private String nomeTitular;

    @JsonProperty
    @Enumerated(EnumType.STRING)
    private CreditorDebit creditorDebit;

    public CardRequest(String numeroCartao, String nomeTitular, CreditorDebit creditorDebit) {
        this.numeroCartao = numeroCartao;
        this.nomeTitular = nomeTitular;
        this.creditorDebit = creditorDebit;
    }

    public Card toModel() {
        return new Card(this.nomeTitular,this.numeroCartao, this.creditorDebit);
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

}
