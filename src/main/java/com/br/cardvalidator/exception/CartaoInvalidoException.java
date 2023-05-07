package com.br.cardvalidator.exception;


public class CartaoInvalidoException extends RuntimeException {
    public CartaoInvalidoException(String message) {
        super(message);
    }
}
