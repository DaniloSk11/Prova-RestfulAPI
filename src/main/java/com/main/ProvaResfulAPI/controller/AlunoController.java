package com.main.ProvaResfulAPI.controller;

import com.main.ProvaResfulAPI.entity.Aluno;
import com.main.ProvaResfulAPI.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody Aluno aluno) {
        try {
            Aluno novoAluno = alunoService.cadastrar(aluno);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoAluno);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Aluno>> listarTodos() {
        return ResponseEntity.ok(alunoService.listarTodos());
    }

    @GetMapping("/ativos")
    public ResponseEntity<List<Aluno>> listarAtivos() {
        return ResponseEntity.ok(alunoService.listarAtivos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            Aluno aluno = alunoService.buscarPorId(id);
            return ResponseEntity.ok(aluno);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Aluno aluno) {
        try {
            Aluno alunoAtualizado = alunoService.atualizar(id, aluno);
            return ResponseEntity.ok(alunoAtualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/inativar")
    public ResponseEntity<?> inativar(@PathVariable Long id) {
        try {
            alunoService.inativar(id);
            return ResponseEntity.ok("Aluno inativado com sucesso");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            alunoService.deletar(id);
            return ResponseEntity.ok("Aluno deletado com sucesso");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}