package com.main.ProvaResfulAPI.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "treinos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Treino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(length = 500)
    private String descricao;

    @Column(length = 50)
    private String dificuldade;

    @Column(nullable = false)
    private Boolean ativo = true;

    @ManyToMany
    @JoinTable(
            name = "treino_aluno",
            joinColumns = @JoinColumn(name = "treino_id"),
            inverseJoinColumns = @JoinColumn(name = "aluno_id")
    )
    @JsonIgnore
    private List<Aluno> alunos = new ArrayList<>();
}