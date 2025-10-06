package com.main.ProvaResfulAPI.repository;

import com.main.ProvaResfulAPI.entity.Treino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TreinoRepository extends JpaRepository<Treino, Long> {

    List<Treino> findByAtivoTrue();
}