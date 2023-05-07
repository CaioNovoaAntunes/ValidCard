package com.br.cardvalidator;

import com.br.cardvalidator.exception.CartaoInvalidoException;
import com.br.cardvalidator.model.Card;
import com.br.cardvalidator.repository.CartaoRepository;
import com.br.cardvalidator.service.CartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

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
    public ResponseEntity salvarCartao(@Valid @RequestBody Card cartao) {
        try {
            if (cartao.getNomeTitular() == null || cartao.getNomeTitular().isEmpty()) {
                throw new CartaoInvalidoException("O número do cartão não pode estar vazio");
            }
            // Restante da lógica para salvar o cartão na base de dados

            return ResponseEntity.status(HttpStatus.CREATED).body("Cartão salvo com sucesso");

        } catch (CartaoInvalidoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}