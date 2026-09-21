package com.pactomais.contabancariaapi.model;

import com.pactomais.contabancariaapi.exception.SaldoInsuficienteException;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class ContaCorrente extends Conta {

    private BigDecimal limite = BigDecimal.ZERO;

    public ContaCorrente() {
    }

    @Override
    public void sacar(BigDecimal valor) {

        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException(
                    "O valor do saque deve ser maior que zero"
            );
        }

        BigDecimal valorDisponivel = getSaldo().add(limite);

        if (valor.compareTo(valorDisponivel) > 0) {
            throw new SaldoInsuficienteException(
                    "Saldo e limite insuficientes"
            );
        }

        setSaldo(getSaldo().subtract(valor));
    }

    public BigDecimal aplicarJuros(BigDecimal taxa) {

        if (taxa.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException(
                    "A taxa deve ser maior que zero"
            );
        }

        if (getSaldo().compareTo(BigDecimal.ZERO) >= 0) {
            throw new RuntimeException(
                    "A conta não possui saldo negativo"
            );
        }

        BigDecimal saldoNegativo = getSaldo().abs();

        BigDecimal juros = saldoNegativo.multiply(taxa);

        setSaldo(getSaldo().subtract(juros));

        return juros;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public void setLimite(BigDecimal limite) {
        this.limite = limite;
    }
}