package com.main.ProvaResfulAPI.repository;

import com.main.ProvaResfulAPI.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    Optional<Aluno> findByCpf(String cpf);

    Optional<Aluno> findByEmail(String email);

    List<Aluno> findByAtivoTrue();

    boolean existsByCpf(String cpf);

    boolean existsByEmail(String email);
}