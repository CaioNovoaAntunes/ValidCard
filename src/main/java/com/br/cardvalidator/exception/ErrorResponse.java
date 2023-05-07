package com.br.cardvalidator.exception;

public class ErrorResponse {
    private int errorCode;
    private String errorMessage;
    private String details;


    public ErrorResponse(String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.details = details;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getDetails() {
        return details;
    }
}