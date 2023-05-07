package com.br.cardvalidator.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class CustomResponseEntityExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        List<String> errors = new ArrayList<>();

        bindingResult.getFieldErrors().forEach(fieldError -> {
            String errorMessage = fieldError.getField() + ": " + fieldError.getDefaultMessage();
            String errorField = fieldError.getCode();

            errors.add(errorMessage);
            errors.add(errorField);

        });
        return ResponseEntity.badRequest().body(errors);
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        if (ex.getCause() instanceof InvalidFormatException) {
            InvalidFormatException e = (InvalidFormatException) ex.getCause();
            if (e.getTargetType().isEnum()) {
                String fieldName = e.getPath().get(0).getFieldName();
                String errorMessage = "O valor fornecido para o campo '" + fieldName + "' é inválido";
                return ResponseEntity.badRequest().body(errorMessage);
            }
        }
        return ResponseEntity.badRequest().build();
    }
}

