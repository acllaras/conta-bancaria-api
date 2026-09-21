package com.pactomais.contabancariaapi.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ContaDepositoTest {

    @Test
    void depositoDeveAumentarSaldo() {

        ContaPoupanca conta = new ContaPoupanca();

        conta.depositar(new BigDecimal("500"));

        assertEquals(
                new BigDecimal("500"),
                conta.getSaldo()
        );
    }
}