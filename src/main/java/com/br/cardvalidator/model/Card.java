package com.br.cardvalidator.model;

import com.br.cardvalidator.enumerador.CreditorDebit;

import javax.persistence.*;

@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numero;
    private String nomeTitular;
    @Enumerated(EnumType.STRING)
    private CreditorDebit creditorDebit ;
    private String dataValidade;
    private int codigoSeguranca;


    public Card(String numeroCartao, CreditorDebit creditorDebit) {
        this.creditorDebit = creditorDebit;
        this.numero = numeroCartao;
    }
    @Deprecated
    public Card() {

    }

    public String getNumero() {
        return numero;
    }

    public String getNomeTitular() {
        return nomeTitular;
    }

    public CreditorDebit getCreditorDebit() {
        return creditorDebit;
    }

    public String getDataValidade() {
        return dataValidade;
    }

    public int getCodigoSeguranca() {
        return codigoSeguranca;
    }
}