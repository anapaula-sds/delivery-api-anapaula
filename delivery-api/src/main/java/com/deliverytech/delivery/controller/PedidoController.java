package com.deliverytech.delivery.controller;

import com.deliverytech.delivery.model.Pedido;
import com.deliverytech.delivery.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin(origins = "*")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<?> cadastrar(@Valid @RequestBody Pedido pedido) {
        try {
            Pedido salvo = pedidoService.cadastrar(pedido);
            return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Optional<Pedido> pedido = pedidoService.buscarPorId(id);
        return pedido.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Pedido>> listarPorCliente(@PathVariable Long clienteId) {
        return ResponseEntity.ok(pedidoService.listarPorCliente(clienteId));
    }

    @GetMapping("/restaurante/{restauranteId}")
    public ResponseEntity<List<Pedido>> listarPorRestaurante(@PathVariable Long restauranteId) {
        return ResponseEntity.ok(pedidoService.listarPorRestaurante(restauranteId));
    }

    @GetMapping("/status")
    public ResponseEntity<List<Pedido>> listarPorStatus(@RequestParam String status) {
        return ResponseEntity.ok(pedidoService.listarPorStatus(status));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<?> atualizarStatus(@PathVariable Long id, @RequestParam String status) {
        try {
            Pedido atualizado = pedidoService.atualizarStatus(id, status);
            return ResponseEntity.ok(atualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        }
    }
}
