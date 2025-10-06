package com.main.ProvaResfulAPI.service;

import com.main.ProvaResfulAPI.entity.Aluno;
import com.main.ProvaResfulAPI.entity.Treino;
import com.main.ProvaResfulAPI.repository.AlunoRepository;
import com.main.ProvaResfulAPI.repository.TreinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TreinoService {

    @Autowired
    private TreinoRepository treinoRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Transactional
    public Treino cadastrar(Treino treino) {
        treino.setAtivo(true);
        return treinoRepository.save(treino);
    }

    public List<Treino> listarTodos() {
        return treinoRepository.findAll();
    }

    public List<Treino> listarAtivos() {
        return treinoRepository.findByAtivoTrue();
    }

    public Treino buscarPorId(Long id) {
        return treinoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Treino não encontrado"));
    }

    @Transactional
    public Treino atualizar(Long id, Treino treinoAtualizado) {
        Treino treino = buscarPorId(id);

        if (treinoAtualizado.getNome() != null) {
            treino.setNome(treinoAtualizado.getNome());
        }

        if (treinoAtualizado.getDescricao() != null) {
            treino.setDescricao(treinoAtualizado.getDescricao());
        }

        if (treinoAtualizado.getDificuldade() != null) {
            treino.setDificuldade(treinoAtualizado.getDificuldade());
        }

        return treinoRepository.save(treino);
    }

    @Transactional
    public Treino associarAluno(Long treinoId, Long alunoId) {
        Treino treino = buscarPorId(treinoId);
        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));

        if (!treino.getAlunos().contains(aluno)) {
            treino.getAlunos().add(aluno);
            aluno.getTreinos().add(treino);
        }

        return treinoRepository.save(treino);
    }

    @Transactional
    public Treino desassociarAluno(Long treinoId, Long alunoId) {
        Treino treino = buscarPorId(treinoId);
        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));

        treino.getAlunos().remove(aluno);
        aluno.getTreinos().remove(treino);

        return treinoRepository.save(treino);
    }

    @Transactional
    public void inativar(Long id) {
        Treino treino = buscarPorId(id);
        treino.setAtivo(false);
        treinoRepository.save(treino);
    }

    @Transactional
    public void deletar(Long id) {
        Treino treino = buscarPorId(id);

        // Regra de negócio: não pode deletar treino com alunos associados
        if (!treino.getAlunos().isEmpty()) {
            throw new IllegalArgumentException("Não é possível remover um treino que possui alunos associados");
        }

        treinoRepository.delete(treino);
    }
}