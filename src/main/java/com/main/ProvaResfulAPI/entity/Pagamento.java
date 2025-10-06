package com.main.ProvaResfulAPI.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "pagamentos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_pagamento", nullable = false)
    private LocalDate dataPagamento;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;

    @Column(nullable = false, length = 50)
    private String status;

    @Column(name = "metodo_pagamento", length = 50)
    private String metodoPagamento;

    @ManyToOne
    @JoinColumn(name = "aluno_id", nullable = false)
    @JsonIgnoreProperties({"pagamentos", "treinos"})
    private Aluno aluno;
}