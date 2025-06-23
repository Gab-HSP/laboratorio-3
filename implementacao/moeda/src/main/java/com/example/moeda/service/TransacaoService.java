package com.example.moeda.service;

import com.example.moeda.dto.TransacaoDTO;
import com.example.moeda.model.pessoa.Pessoa;
import com.example.moeda.model.transacao.Transacao;
import com.example.moeda.model.instituicao.Instituicao;
import com.example.moeda.repository.PessoaRepository;
import com.example.moeda.repository.InstituicaoRepository;
import com.example.moeda.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TransacaoService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private InstituicaoRepository instituicaoRepository;

    @Autowired
    private TransacaoRepository transacaoRepository;

    public Optional<Transacao> processarTransacao(TransacaoDTO dto) {
        Optional<Pessoa> remetenteOpt = pessoaRepository.findById(dto.getRemetenteId());
        Optional<Pessoa> destinatarioOpt = pessoaRepository.findById(dto.getDestinatarioId());
        Optional<Instituicao> instituicaoOpt = instituicaoRepository.findById(dto.getInstituicaoId());

        if (remetenteOpt.isEmpty() || destinatarioOpt.isEmpty() || instituicaoOpt.isEmpty()) {
            return Optional.empty();
        }

        Pessoa remetente = remetenteOpt.get();
        Pessoa destinatario = destinatarioOpt.get();

        if (remetente.getSaldo() < dto.getValor()) {
            return Optional.empty();
        }

        remetente.setSaldo(remetente.getSaldo() - dto.getValor());
        destinatario.setSaldo(destinatario.getSaldo() + dto.getValor());

        pessoaRepository.save(remetente);
        pessoaRepository.save(destinatario);

        Transacao transacao = new Transacao();
        transacao.setData(LocalDateTime.now());
        transacao.setRemetente(remetente);
        transacao.setDestinatario(destinatario);
        transacao.setValor(dto.getValor());

        return Optional.of(transacaoRepository.save(transacao));
    }
}
