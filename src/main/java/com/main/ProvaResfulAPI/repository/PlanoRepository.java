
package com.main.ProvaResfulAPI.repository;

import com.main.ProvaResfulAPI.entity.Plano;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanoRepository extends JpaRepository<Plano, Long> {

    List<Plano> findByAtivoTrue();

    boolean existsByNome(String nome);
}