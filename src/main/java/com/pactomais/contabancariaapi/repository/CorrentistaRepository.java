package com.pactomais.contabancariaapi.repository;

import com.pactomais.contabancariaapi.model.Correntista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CorrentistaRepository extends JpaRepository<Correntista, Long> {
}