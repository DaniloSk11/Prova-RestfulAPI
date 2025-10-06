package com.main.ProvaResfulAPI.service;

import com.main.ProvaResfulAPI.entity.Aluno;
import com.main.ProvaResfulAPI.entity.Plano;
import com.main.ProvaResfulAPI.repository.AlunoRepository;
import com.main.ProvaResfulAPI.repository.PlanoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private PlanoRepository planoRepository;

    @Transactional
    public Aluno cadastrar(Aluno aluno) {
        if (alunoRepository.existsByCpf(aluno.getCpf())) {
            throw new IllegalArgumentException("CPF já cadastrado");
        }

        if (alunoRepository.existsByEmail(aluno.getEmail())) {
            throw new IllegalArgumentException("Email já cadastrado");
        }

        if (aluno.getPlano() != null) {
            Plano plano = planoRepository.findById(aluno.getPlano().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Plano não encontrado"));
            aluno.setPlano(plano);
        }

        aluno.setDataMatricula(LocalDate.now());
        aluno.setAtivo(true);

        return alunoRepository.save(aluno);
    }

    public List<Aluno> listarTodos() {
        return alunoRepository.findAll();
    }

    public List<Aluno> listarAtivos() {
        return alunoRepository.findByAtivoTrue();
    }

    public Aluno buscarPorId(Long id) {
        return alunoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));
    }

    @Transactional
    public Aluno atualizar(Long id, Aluno alunoAtualizado) {
        Aluno aluno = buscarPorId(id);

        if (alunoAtualizado.getCpf() != null && !alunoAtualizado.getCpf().equals(aluno.getCpf())) {
            if (alunoRepository.existsByCpf(alunoAtualizado.getCpf())) {
                throw new IllegalArgumentException("CPF já cadastrado");
            }
            aluno.setCpf(alunoAtualizado.getCpf());
        }

        if (alunoAtualizado.getEmail() != null && !alunoAtualizado.getEmail().equals(aluno.getEmail())) {
            if (alunoRepository.existsByEmail(alunoAtualizado.getEmail())) {
                throw new IllegalArgumentException("Email já cadastrado");
            }
            aluno.setEmail(alunoAtualizado.getEmail());
        }

        if (alunoAtualizado.getNome() != null) {
            aluno.setNome(alunoAtualizado.getNome());
        }

        if (alunoAtualizado.getTelefone() != null) {
            aluno.setTelefone(alunoAtualizado.getTelefone());
        }

        if (alunoAtualizado.getDataNascimento() != null) {
            aluno.setDataNascimento(alunoAtualizado.getDataNascimento());
        }

        if (alunoAtualizado.getPlano() != null) {
            Plano plano = planoRepository.findById(alunoAtualizado.getPlano().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Plano não encontrado"));
            aluno.setPlano(plano);
        }

        return alunoRepository.save(aluno);
    }

    @Transactional
    public void inativar(Long id) {
        Aluno aluno = buscarPorId(id);
        aluno.setAtivo(false);
        alunoRepository.save(aluno);
    }

    @Transactional
    public void deletar(Long id) {
        Aluno aluno = buscarPorId(id);
        alunoRepository.delete(aluno);
    }
}