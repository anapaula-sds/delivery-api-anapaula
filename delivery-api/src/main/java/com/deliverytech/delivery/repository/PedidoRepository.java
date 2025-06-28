package com.deliverytech.delivery_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.deliverytech.delivery_api.entity.Pedido;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    // Buscar por status (ex: PENDENTE, ENTREGUE)
    List<Pedido> findByStatusIgnoreCase(String status);

    // Buscar pedidos por cliente
    List<Pedido> findByClienteId(Long clienteId);

    // Buscar pedidos por restaurante
    List<Pedido> findByRestauranteId(Long restauranteId);

    // Contar pedidos com status específico
    @Query("SELECT COUNT(p) FROM Pedido p WHERE LOWER(p.status) = LOWER(:status)")
    Long countByStatus(String status);
}
