package com.br.cardvalidator;

import com.br.cardvalidator.dto.CardRequest;
import com.br.cardvalidator.model.Card;
import com.br.cardvalidator.repository.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
    public ResponseEntity<?> salvarCartao(@Valid @RequestBody CardRequest cartao) {
        Card card = cartao.toModel();
            cartaoRepository.save(card);
            return ResponseEntity.status(HttpStatus.CREATED).body("Cartão salvo com sucesso");
    }

}