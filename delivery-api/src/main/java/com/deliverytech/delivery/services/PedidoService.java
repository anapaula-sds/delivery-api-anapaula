package com.deliverytech.delivery.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deliverytech.delivery.entity.Pedido;
import com.deliverytech.delivery.repository.PedidoRepository;

@Service
@Transactional
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido cadastrar(Pedido pedido) {
        validarDadosPedido(pedido);
        pedido.setStatus("PENDENTE"); // Status padrão
        return pedidoRepository.save(pedido);
    }

    @Transactional(readOnly = true)
    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Pedido> buscarPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    @Transactional
    public Pedido atualizar(Long id, Pedido pedidoAtualizado) {
        Pedido pedidoExistente = pedidoRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Pedido com ID " + id + " não encontrado."));

        // Atualize os campos necessários:
        pedidoExistente.setStatus(pedidoAtualizado.getStatus());


        return pedidoRepository.save(pedidoExistente);
    }

    @Transactional
    public void excluir(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Pedido com ID " + id + " não encontrado."));

        pedidoRepository.delete(pedido);
    }

    @Transactional(readOnly = true)
    public List<Pedido> listarPorCliente(Long clienteId) {
        return pedidoRepository.findByClienteId(clienteId);
    }

    @Transactional(readOnly = true)
    public List<Pedido> listarPorRestaurante(Long restauranteId) {
        return pedidoRepository.findByRestauranteId(restauranteId);
    }

    @Transactional(readOnly = true)
    public List<Pedido> listarPorStatus(String status) {
        return pedidoRepository.findByStatus(status);
    }

    public Pedido atualizarStatus(Long id, String novoStatus) {
        Pedido pedido = buscarPorId(id)
            .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado: " + id));

        pedido.setStatus(novoStatus);
        return pedidoRepository.save(pedido);
    }

    @Transactional(readOnly = true)
    public List<Pedido> buscarPorStatus(String status) {
        List<Pedido> pedidos = pedidoRepository.findByStatus(status);

        if (pedidos.isEmpty()) {
            throw new IllegalArgumentException("Nenhum pedido encontrado com o status: " + status);
        }

        return pedidos;
    }

    private void validarDadosPedido(Pedido pedido) {
        if (pedido.getNumeroPedido() == null || pedido.getNumeroPedido().isBlank()) {
            throw new IllegalArgumentException("Número do pedido é obrigatório");
        }
        if (pedido.getClienteId() == null || pedido.getRestauranteId() == null) {
            throw new IllegalArgumentException("Cliente e restaurante são obrigatórios");
        }
    }
}
