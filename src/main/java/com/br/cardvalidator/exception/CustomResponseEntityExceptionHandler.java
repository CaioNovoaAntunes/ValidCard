package com.br.cardvalidator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception ex) {
        // Construa uma mensagem de erro personalizada com base na exceção ou em qualquer outro critério
        String errorMessage = "Ocorreu um erro durante o processamento da requisição.";

        // Crie um objeto de resposta de erro adequado, por exemplo, ErrorResponse
        ErrorResponse errorResponse = new ErrorResponse(errorMessage);

        // Retorne a resposta de erro com status apropriado, por exemplo, 500 Internal Server Error
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        List<String> errors = new ArrayList<>();

        bindingResult.getFieldErrors().forEach(fieldError -> {
            String errorMessage = fieldError.getField() + ": " + fieldError.getDefaultMessage();
            String errorField = fieldError.getCode();

            errors.add(errorMessage);

        });

        return ResponseEntity.badRequest().body(errors);
    }
}

