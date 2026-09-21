package com.pactomais.contabancariaapi.service;

import com.pactomais.contabancariaapi.dto.AberturaContaRequest;
import com.pactomais.contabancariaapi.exception.ContaNaoEncontradaException;
import com.pactomais.contabancariaapi.model.Conta;
import com.pactomais.contabancariaapi.model.ContaCorrente;
import com.pactomais.contabancariaapi.model.ContaPoupanca;
import com.pactomais.contabancariaapi.model.Correntista;
import com.pactomais.contabancariaapi.model.TipoTransacao;
import com.pactomais.contabancariaapi.model.Transacao;
import com.pactomais.contabancariaapi.repository.ContaRepository;
import com.pactomais.contabancariaapi.repository.TransacaoRepository;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ContaService {

    private final ContaRepository contaRepository;
    private final CorrentistaService correntistaService;
    private final TransacaoRepository transacaoRepository;

    public ContaService(
            ContaRepository contaRepository,
            CorrentistaService correntistaService,
            TransacaoRepository transacaoRepository) {

        this.contaRepository = contaRepository;
        this.correntistaService = correntistaService;
        this.transacaoRepository = transacaoRepository;
    }

    public Conta abrirConta(AberturaContaRequest request) {

        Correntista correntista =
                correntistaService.buscarPorId(
                        request.getCorrentistaId()
                );

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

            throw new RuntimeException(
                    "Tipo de conta inválido"
            );
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
                .orElseThrow(() ->
                        new ContaNaoEncontradaException(
                                "Conta não encontrada"
                        ));
    }

    public Conta depositar(Long contaId, BigDecimal valor) {

        Conta conta = buscarPorId(contaId);

        conta.depositar(valor);

        Conta contaSalva = contaRepository.save(conta);

        Transacao transacao = new Transacao();
        transacao.setTipo(TipoTransacao.DEPOSITO);
        transacao.setValor(valor);
        transacao.setData(LocalDateTime.now());
        transacao.setConta(conta);

        transacaoRepository.save(transacao);

        return contaSalva;
    }

    public Conta sacar(Long contaId, BigDecimal valor) {

        Conta conta = buscarPorId(contaId);

        conta.sacar(valor);

        Conta contaSalva = contaRepository.save(conta);

        Transacao transacao = new Transacao();
        transacao.setTipo(TipoTransacao.SAQUE);
        transacao.setValor(valor);
        transacao.setData(LocalDateTime.now());
        transacao.setConta(conta);

        transacaoRepository.save(transacao);

        return contaSalva;
    }

    public List<Transacao> buscarExtrato(Long contaId) {

        buscarPorId(contaId);

        return transacaoRepository
                .findByContaIdOrderByDataDesc(contaId);
    }

    public Conta aplicarRendimento(
            Long contaId,
            BigDecimal taxa) {

        Conta conta = buscarPorId(contaId);

        if (!(conta instanceof ContaPoupanca)) {
            throw new RuntimeException(
                    "Rendimento só pode ser aplicado em conta poupança"
            );
        }

        ContaPoupanca contaPoupanca =
                (ContaPoupanca) conta;

        BigDecimal rendimento =
                contaPoupanca.aplicarRendimento(taxa);

        Conta contaSalva =
                contaRepository.save(contaPoupanca);

        Transacao transacao = new Transacao();

        transacao.setTipo(
                TipoTransacao.RENDIMENTO
        );

        transacao.setValor(rendimento);
        transacao.setData(LocalDateTime.now());
        transacao.setConta(contaPoupanca);

        transacaoRepository.save(transacao);

        return contaSalva;
    }
}