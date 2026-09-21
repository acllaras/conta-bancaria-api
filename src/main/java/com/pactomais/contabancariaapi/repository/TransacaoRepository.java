package com.pactomais.contabancariaapi.repository;

import com.pactomais.contabancariaapi.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

    List<Transacao> findByContaIdOrderByDataDesc(Long contaId);
}