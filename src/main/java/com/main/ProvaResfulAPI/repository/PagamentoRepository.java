package com.main.ProvaResfulAPI.repository;

import com.main.ProvaResfulAPI.entity.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

    List<Pagamento> findByAlunoId(Long alunoId);
}