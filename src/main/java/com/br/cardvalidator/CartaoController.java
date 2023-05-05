package com.br.cardvalidator;

import com.br.cardvalidator.model.Card;
import com.br.cardvalidator.repository.CartaoRepository;
import com.br.cardvalidator.service.CartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("card")
public class CartaoController {

    @Autowired
    private CartaoService cartaoService;
    @Autowired
    private CartaoRepository cartaoRepository;

    @PostMapping("/newcard")
    public ResponseEntity salvarCartao(@RequestBody Card card) {
       cartaoRepository.save(card);
    return ResponseEntity.ok().build();
    }
}

