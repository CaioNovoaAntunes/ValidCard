package com.br.cardvalidator;

import com.br.cardvalidator.dto.NewCardRequest;
import com.br.cardvalidator.model.Card;
import com.br.cardvalidator.repository.NewCardRepository;
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
public class NewCardController {
    @Autowired
    private NewCardRepository newCardRepository;

    @Autowired
    CartaoCreditoValidator cartaoCreditoValidator;


    @Autowired
    public NewCardController(NewCardRepository newCardRepository) {
        this.newCardRepository = newCardRepository;
    }


    @PostMapping("/cartoes")
    public ResponseEntity<?> saveCard(@Valid @RequestBody NewCardRequest newCardReq) {
        String numeroCartao = newCardReq.getNumeroCartao();

        // Verificar se o cartão de crédito é válido
        boolean isCartaoValido = cartaoCreditoValidator.validarCartaoCredito(numeroCartao);
        System.out.println(numeroCartao);

        if (!isCartaoValido) {
            return ResponseEntity.badRequest().body("Número de cartão de crédito inválido");
        }

        // Se o cartão for válido, continue com a operação de salvamento
        Card saveNewCard = newCardReq.toModel();
        newCardRepository.save(saveNewCard);

        return ResponseEntity.status(HttpStatus.CREATED).body("Cartão salvo com sucesso");
    }
}