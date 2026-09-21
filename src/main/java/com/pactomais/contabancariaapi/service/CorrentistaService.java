package com.pactomais.contabancariaapi.service;

import com.pactomais.contabancariaapi.model.Correntista;
import com.pactomais.contabancariaapi.repository.CorrentistaRepository;
import com.pactomais.contabancariaapi.exception.CorrentistaNaoEncontradoException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CorrentistaService {

    private final CorrentistaRepository repository;

    public CorrentistaService(CorrentistaRepository repository) {
        this.repository = repository;
    }

    public Correntista cadastrar(Correntista correntista) {
        return repository.save(correntista);
    }

    public List<Correntista> listarTodos() {
        return repository.findAll();
    }

    public Correntista buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> 
                        new CorrentistaNaoEncontradoException(
                            "Correntista não encontrado"));
    }
}