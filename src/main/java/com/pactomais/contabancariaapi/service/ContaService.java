package com.pactomais.contabancariaapi.service;

import com.pactomais.contabancariaapi.dto.AberturaContaRequest;
import com.pactomais.contabancariaapi.model.Conta;
import com.pactomais.contabancariaapi.model.ContaCorrente;
import com.pactomais.contabancariaapi.model.ContaPoupanca;
import com.pactomais.contabancariaapi.model.Correntista;
import com.pactomais.contabancariaapi.repository.ContaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ContaService {

    private final ContaRepository contaRepository;
    private final CorrentistaService correntistaService;

    public ContaService(
            ContaRepository contaRepository,
            CorrentistaService correntistaService) {

        this.contaRepository = contaRepository;
        this.correntistaService = correntistaService;
    }

    public Conta abrirConta(AberturaContaRequest request) {

        Correntista correntista =
                correntistaService.buscarPorId(request.getCorrentistaId());

        Conta conta;

        if ("CORRENTE".equalsIgnoreCase(request.getTipo())) {

            ContaCorrente contaCorrente = new ContaCorrente();

            BigDecimal limite = request.getLimite() != null
                    ? request.getLimite()
                    : BigDecimal.ZERO;

            contaCorrente.setLimite(limite);

            conta = contaCorrente;

        } else if ("POUPANCA".equalsIgnoreCase(request.getTipo())) {

            conta = new ContaPoupanca();

        } else {

            throw new RuntimeException("Tipo de conta inválido");
        }

        conta.setNumero(request.getNumero());
        conta.setCorrentista(correntista);

        return contaRepository.save(conta);
    }

    public List<Conta> listarTodas() {
        return contaRepository.findAll();
    }

    public Conta buscarPorId(Long id) {
        return contaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));
    }
}