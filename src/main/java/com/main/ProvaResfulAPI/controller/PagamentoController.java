package com.main.ProvaResfulAPI.controller;

import com.main.ProvaResfulAPI.entity.Pagamento;
import com.main.ProvaResfulAPI.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody Pagamento pagamento) {
        try {
            Pagamento novoPagamento = pagamentoService.registrar(pagamento);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoPagamento);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Pagamento>> listarTodos() {
        return ResponseEntity.ok(pagamentoService.listarTodos());
    }

    @GetMapping("/aluno/{alunoId}")
    public ResponseEntity<List<Pagamento>> listarPorAluno(@PathVariable Long alunoId) {
        return ResponseEntity.ok(pagamentoService.listarPorAluno(alunoId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            Pagamento pagamento = pagamentoService.buscarPorId(id);
            return ResponseEntity.ok(pagamento);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Pagamento pagamento) {
        try {
            Pagamento pagamentoAtualizado = pagamentoService.atualizar(id, pagamento);
            return ResponseEntity.ok(pagamentoAtualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            pagamentoService.deletar(id);
            return ResponseEntity.ok("Pagamento deletado com sucesso");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}