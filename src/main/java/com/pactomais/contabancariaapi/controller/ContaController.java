package com.pactomais.contabancariaapi.controller;

import com.pactomais.contabancariaapi.dto.AberturaContaRequest;
import com.pactomais.contabancariaapi.dto.OperacaoRequest;
import com.pactomais.contabancariaapi.model.Conta;
import com.pactomais.contabancariaapi.model.Transacao;
import com.pactomais.contabancariaapi.service.ContaService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/contas")
public class ContaController {

    private final ContaService service;

    public ContaController(ContaService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Conta abrirConta(
            @RequestBody AberturaContaRequest request) {

        return service.abrirConta(request);
    }

    @GetMapping
    public List<Conta> listarTodas() {
        return service.listarTodas();
    }

    @GetMapping("/{id}")
    public Conta buscarPorId(
            @PathVariable Long id) {

        return service.buscarPorId(id);
    }

    @PostMapping("/{id}/depositos")
    public Conta depositar(
            @PathVariable Long id,
            @RequestBody OperacaoRequest request) {

        return service.depositar(
                id,
                request.getValor()
        );
    }

    @PostMapping("/{id}/saques")
    public Conta sacar(
            @PathVariable Long id,
            @RequestBody OperacaoRequest request) {

        return service.sacar(
                id,
                request.getValor()
        );
    }

    @GetMapping("/{id}/transacoes")
    public List<Transacao> buscarExtrato(
            @PathVariable Long id) {

        return service.buscarExtrato(id);
    }

    @PostMapping("/{id}/rendimento")
    public Conta aplicarRendimento(
            @PathVariable Long id,
            @RequestParam BigDecimal taxa) {

        return service.aplicarRendimento(
                id,
                taxa
        );
    }
}