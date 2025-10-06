package com.main.ProvaResfulAPI.controller;

import com.main.ProvaResfulAPI.entity.Plano;
import com.main.ProvaResfulAPI.service.PlanoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/planos")
public class PlanoController {

    @Autowired
    private PlanoService planoService;

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody Plano plano) {
        try {
            Plano novoPlano = planoService.cadastrar(plano);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoPlano);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Plano>> listarTodos() {
        return ResponseEntity.ok(planoService.listarTodos());
    }

    @GetMapping("/ativos")
    public ResponseEntity<List<Plano>> listarAtivos() {
        return ResponseEntity.ok(planoService.listarAtivos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            Plano plano = planoService.buscarPorId(id);
            return ResponseEntity.ok(plano);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Plano plano) {
        try {
            Plano planoAtualizado = planoService.atualizar(id, plano);
            return ResponseEntity.ok(planoAtualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/inativar")
    public ResponseEntity<?> inativar(@PathVariable Long id) {
        try {
            planoService.inativar(id);
            return ResponseEntity.ok("Plano inativado com sucesso");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            planoService.deletar(id);
            return ResponseEntity.ok("Plano deletado com sucesso");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}