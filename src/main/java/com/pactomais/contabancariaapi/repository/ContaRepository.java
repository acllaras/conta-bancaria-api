package com.pactomais.contabancariaapi.repository;

import com.pactomais.contabancariaapi.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, Long> {
}