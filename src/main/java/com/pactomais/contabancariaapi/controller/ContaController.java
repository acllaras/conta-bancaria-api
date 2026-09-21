package com.pactomais.contabancariaapi.controller;

import com.pactomais.contabancariaapi.dto.AberturaContaRequest;
import com.pactomais.contabancariaapi.model.Conta;
import com.pactomais.contabancariaapi.service.ContaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public Conta abrirConta(@RequestBody AberturaContaRequest request) {
        return service.abrirConta(request);
    }

    @GetMapping
    public List<Conta> listarTodas() {
        return service.listarTodas();
    }

    @GetMapping("/{id}")
    public Conta buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }
}