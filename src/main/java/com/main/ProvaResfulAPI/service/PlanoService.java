package com.main.ProvaResfulAPI.service;

import com.main.ProvaResfulAPI.entity.Plano;
import com.main.ProvaResfulAPI.repository.PlanoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PlanoService {

    @Autowired
    private PlanoRepository planoRepository;

    @Transactional
    public Plano cadastrar(Plano plano) {
        if (planoRepository.existsByNome(plano.getNome())) {
            throw new IllegalArgumentException("Plano com este nome já existe");
        }

        plano.setAtivo(true);
        return planoRepository.save(plano);
    }

    public List<Plano> listarTodos() {
        return planoRepository.findAll();
    }

    public List<Plano> listarAtivos() {
        return planoRepository.findByAtivoTrue();
    }

    public Plano buscarPorId(Long id) {
        return planoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Plano não encontrado"));
    }

    @Transactional
    public Plano atualizar(Long id, Plano planoAtualizado) {
        Plano plano = buscarPorId(id);

        if (planoAtualizado.getNome() != null) {
            plano.setNome(planoAtualizado.getNome());
        }

        if (planoAtualizado.getDescricao() != null) {
            plano.setDescricao(planoAtualizado.getDescricao());
        }

        if (planoAtualizado.getValor() != null) {
            plano.setValor(planoAtualizado.getValor());
        }

        if (planoAtualizado.getDuracao() != null) {
            plano.setDuracao(planoAtualizado.getDuracao());
        }

        return planoRepository.save(plano);
    }

    @Transactional
    public void inativar(Long id) {
        Plano plano = buscarPorId(id);
        plano.setAtivo(false);
        planoRepository.save(plano);
    }

    @Transactional
    public void deletar(Long id) {
        Plano plano = buscarPorId(id);
        planoRepository.delete(plano);
    }
}