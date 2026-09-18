package com.pactomais.contabancariaapi.model;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class ContaPoupanca extends Conta {

    public ContaPoupanca() {
    }

    @Override
    public void sacar(BigDecimal valor) {

        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException(
                    "O valor do saque deve ser maior que zero"
            );
        }

        if (valor.compareTo(getSaldo()) > 0) {
            throw new RuntimeException(
                    "Saldo insuficiente"
            );
        }

        setSaldo(getSaldo().subtract(valor));
    }
}