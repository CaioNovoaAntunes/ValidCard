package com.br.cardvalidator.exception;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.stream.Collectors;

public class ValidationUtils {
    public static List<String> getErrorsMessage(BindingResult bindingResult){
        return bindingResult.getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
    }
}
