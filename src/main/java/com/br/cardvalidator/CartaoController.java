package com.br.cardvalidator;

import com.br.cardvalidator.dto.CardRequest;
import com.br.cardvalidator.exception.CartaoInvalidoException;
import com.br.cardvalidator.exception.ValidationUtils;
import com.br.cardvalidator.model.Card;
import com.br.cardvalidator.repository.CartaoRepository;
import com.br.cardvalidator.service.CartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping
public class CartaoController {
    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    public CartaoController(CartaoRepository cartaoRepository) {
        this.cartaoRepository = cartaoRepository;
    }

    @PostMapping("/cartoes")
    public ResponseEntity<?> salvarCartao(@Valid @RequestBody CardRequest cartao, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = ValidationUtils.getErrorsMessage(bindingResult);
            return ResponseEntity.badRequest().body(errors);
        }
        Card card = cartao.toModel();
            cartaoRepository.save(card);
            return ResponseEntity.status(HttpStatus.CREATED).body("Cartão salvo com sucesso");
    }

}