package com.pactomais.contabancariaapi.dto;

import java.math.BigDecimal;

public class AberturaContaRequest {

    private String numero;
    private String tipo;
    private Long correntistaId;
    private BigDecimal limite;

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Long getCorrentistaId() {
        return correntistaId;
    }

    public void setCorrentistaId(Long correntistaId) {
        this.correntistaId = correntistaId;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public void setLimite(BigDecimal limite) {
        this.limite = limite;
    }
}