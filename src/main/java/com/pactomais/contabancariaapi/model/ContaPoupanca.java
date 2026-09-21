package com.pactomais.contabancariaapi.model;

import com.pactomais.contabancariaapi.exception.SaldoInsuficienteException;

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
            throw new SaldoInsuficienteException(
                    "Saldo insuficiente"
            );
        }

        setSaldo(getSaldo().subtract(valor));
    }

    public BigDecimal aplicarRendimento(BigDecimal taxa) {

        if (taxa.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException(
                    "A taxa deve ser maior que zero"
            );
        }

        BigDecimal rendimento = getSaldo().multiply(taxa);

        setSaldo(getSaldo().add(rendimento));

        return rendimento;
    }
}