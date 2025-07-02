package com.deliverytech.delivery.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.deliverytech.delivery.entity.Cliente;
import com.deliverytech.delivery.entity.Pedido;
import com.deliverytech.delivery.enums.StatusPedido;

@Repository
public interface PedidoRepository extends JpaRepository <Pedido, Long> {

    // Buscar pedidos por cliente ID
    List<Pedido> findByClienteIdOrderByDataPedidoDesc(Long clienteId);

    // Pedidos por cliente
    List<Pedido> findByClienteId(Long clienteId);

    // Pedidos por status
    List<Pedido> findByStatus(StatusPedido status);

    // 10 pedidos mais recentes
    List<Pedido> findTop10ByOrderByDataPedidoDesc();

    // Pedidos por período
    List<Pedido> findByDataPedidoBetween(LocalDateTime inicio, LocalDateTime fim);

}