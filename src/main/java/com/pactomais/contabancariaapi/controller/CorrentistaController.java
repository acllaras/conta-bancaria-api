package com.pactomais.contabancariaapi.controller;

import com.pactomais.contabancariaapi.model.Correntista;
import com.pactomais.contabancariaapi.service.CorrentistaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/correntistas")
public class CorrentistaController {

    private final CorrentistaService service;

    public CorrentistaController(CorrentistaService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Correntista cadastrar(@RequestBody Correntista correntista) {
        return service.cadastrar(correntista);
    }

    @GetMapping
    public List<Correntista> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public Correntista buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }
}