package com.pactomais.contabancariaapi.exception;

public class CorrentistaNaoEncontradoException extends RuntimeException {

    public CorrentistaNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}