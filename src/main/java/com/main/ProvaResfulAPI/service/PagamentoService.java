package com.main.ProvaResfulAPI.service;

import com.main.ProvaResfulAPI.entity.Aluno;
import com.main.ProvaResfulAPI.entity.Pagamento;
import com.main.ProvaResfulAPI.repository.AlunoRepository;
import com.main.ProvaResfulAPI.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Transactional
    public Pagamento registrar(Pagamento pagamento) {
        Aluno aluno = alunoRepository.findById(pagamento.getAluno().getId())
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));

        // Regra de negócio: data preenchida automaticamente
        pagamento.setDataPagamento(LocalDate.now());
        pagamento.setAluno(aluno);

        return pagamentoRepository.save(pagamento);
    }

    public List<Pagamento> listarTodos() {
        return pagamentoRepository.findAll();
    }

    public List<Pagamento> listarPorAluno(Long alunoId) {
        return pagamentoRepository.findByAlunoId(alunoId);
    }

    public Pagamento buscarPorId(Long id) {
        return pagamentoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pagamento não encontrado"));
    }

    @Transactional
    public Pagamento atualizar(Long id, Pagamento pagamentoAtualizado) {
        Pagamento pagamento = buscarPorId(id);

        if (pagamentoAtualizado.getValor() != null) {
            pagamento.setValor(pagamentoAtualizado.getValor());
        }

        if (pagamentoAtualizado.getStatus() != null) {
            pagamento.setStatus(pagamentoAtualizado.getStatus());
        }

        if (pagamentoAtualizado.getMetodoPagamento() != null) {
            pagamento.setMetodoPagamento(pagamentoAtualizado.getMetodoPagamento());
        }

        return pagamentoRepository.save(pagamento);
    }

    @Transactional
    public void deletar(Long id) {
        Pagamento pagamento = buscarPorId(id);
        pagamentoRepository.delete(pagamento);
    }
}