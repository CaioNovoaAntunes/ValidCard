package com.br.cardvalidator.repository;

import com.br.cardvalidator.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaoRepository extends JpaRepository<Card, Long> {
}
