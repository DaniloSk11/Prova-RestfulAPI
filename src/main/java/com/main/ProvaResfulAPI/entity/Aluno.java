package com.main.ProvaResfulAPI.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "alunos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 15)
    private String telefone;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "data_matricula", nullable = false)
    private LocalDate dataMatricula;

    @Column(nullable = false)
    private Boolean ativo = true;

    @ManyToOne
    @JoinColumn(name = "plano_id")
    private Plano plano;

    @ManyToMany(mappedBy = "alunos")
    @JsonIgnore
    private List<Treino> treinos = new ArrayList<>();

    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Pagamento> pagamentos = new ArrayList<>();
}