package com.pactomais.contabancariaapi.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ContaPoupancaTest {

    @Test
    void devePermitirSaqueQuandoHaSaldo() {

        ContaPoupanca conta = new ContaPoupanca();

        conta.depositar(new BigDecimal("500"));

        conta.sacar(new BigDecimal("200"));

        assertEquals(
                new BigDecimal("300"),
                conta.getSaldo()
        );
    }

    @Test
    void naoDevePermitirSaqueAcimaDoSaldo() {

        ContaPoupanca conta = new ContaPoupanca();

        conta.depositar(new BigDecimal("500"));

        assertThrows(
                RuntimeException.class,
                () -> conta.sacar(new BigDecimal("600"))
        );
    }
}