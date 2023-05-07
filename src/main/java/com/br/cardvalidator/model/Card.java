package com.br.cardvalidator.model;

import com.br.cardvalidator.enumerador.TypeCard;

import javax.persistence.*;

@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numero;
    private String nomeTitular;
    @Enumerated(EnumType.STRING)
    private TypeCard typeCard;
    private String dataValidade;
    private int codigoSeguranca;



    @Deprecated
    public Card() {

    }

    public Card(String nomeTitular, String numeroCartao, TypeCard typeCard) {
        this.nomeTitular = nomeTitular;
        this.typeCard = typeCard;
        this.numero = numeroCartao;
    }


    public String getNumero() {
        return numero;
    }

    public String getNomeTitular() {
        return nomeTitular;
    }

    public TypeCard getCreditorDebit() {
        return typeCard;
    }

    public String getDataValidade() {
        return dataValidade;
    }

    public int getCodigoSeguranca() {
        return codigoSeguranca;
    }
}