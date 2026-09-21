package com.pactomais.contabancariaapi.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ContaCorrenteTest {

    @Test
    void devePermitirSaqueUsandoLimite() {

        ContaCorrente conta = new ContaCorrente();

        conta.setLimite(new BigDecimal("500"));
        conta.depositar(new BigDecimal("300"));

        conta.sacar(new BigDecimal("700"));

        assertEquals(
                new BigDecimal("-400"),
                conta.getSaldo()
        );
    }

    @Test
    void naoDevePermitirSaqueAcimaDoSaldoMaisLimite() {

        ContaCorrente conta = new ContaCorrente();

        conta.setLimite(new BigDecimal("500"));
        conta.depositar(new BigDecimal("300"));

        assertThrows(
                RuntimeException.class,
                () -> conta.sacar(new BigDecimal("900"))
        );
    }
}