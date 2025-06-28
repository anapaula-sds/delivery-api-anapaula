package com.deliverytech.delivery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.deliverytech.delivery.entity.Produto;
import com.deliverytech.delivery.services.ProdutoService;

import java.util.List;
import java.util.Optional;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<?> cadastrar(@Valid @RequestBody Produto produto) {
        try {
            Produto salvo = produtoService.cadastrar(produto);
            return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Optional<Produto> produto = produtoService.buscarPorId(id);
        return produto.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Produto>> listarDisponiveis() {
        return ResponseEntity.ok(produtoService.listarDisponiveis());
    }

    @GetMapping("/categoria")
    public ResponseEntity<List<Produto>> listarPorCategoria(@RequestParam String categoria) {
        return ResponseEntity.ok(produtoService.listarPorCategoria(categoria));
    }

    @GetMapping("/restaurante/{restauranteId}")
    public ResponseEntity<List<Produto>> listarPorRestaurante(@PathVariable Long restauranteId) {
        return ResponseEntity.ok(produtoService.listarPorRestaurante(restauranteId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Produto produto) {
        try {
            Produto atualizado = produtoService.atualizar(id, produto);
            return ResponseEntity.ok(atualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        }
    }
}
