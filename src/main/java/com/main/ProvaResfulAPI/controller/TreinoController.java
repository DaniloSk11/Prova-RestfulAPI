package com.main.ProvaResfulAPI.controller;

import com.main.ProvaResfulAPI.entity.Treino;
import com.main.ProvaResfulAPI.service.TreinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/treinos")
public class TreinoController {

    @Autowired
    private TreinoService treinoService;

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody Treino treino) {
        try {
            Treino novoTreino = treinoService.cadastrar(treino);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoTreino);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Treino>> listarTodos() {
        return ResponseEntity.ok(treinoService.listarTodos());
    }

    @GetMapping("/ativos")
    public ResponseEntity<List<Treino>> listarAtivos() {
        return ResponseEntity.ok(treinoService.listarAtivos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            Treino treino = treinoService.buscarPorId(id);
            return ResponseEntity.ok(treino);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Treino treino) {
        try {
            Treino treinoAtualizado = treinoService.atualizar(id, treino);
            return ResponseEntity.ok(treinoAtualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{treinoId}/alunos/{alunoId}")
    public ResponseEntity<?> associarAluno(@PathVariable Long treinoId, @PathVariable Long alunoId) {
        try {
            Treino treino = treinoService.associarAluno(treinoId, alunoId);
            return ResponseEntity.ok(treino);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{treinoId}/alunos/{alunoId}")
    public ResponseEntity<?> desassociarAluno(@PathVariable Long treinoId, @PathVariable Long alunoId) {
        try {
            Treino treino = treinoService.desassociarAluno(treinoId, alunoId);
            return ResponseEntity.ok(treino);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/inativar")
    public ResponseEntity<?> inativar(@PathVariable Long id) {
        try {
            treinoService.inativar(id);
            return ResponseEntity.ok("Treino inativado com sucesso");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            treinoService.deletar(id);
            return ResponseEntity.ok("Treino deletado com sucesso");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}