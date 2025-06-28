package com.deliverytech.delivery_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deliverytech.delivery_api.entity.Produto;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    // Buscar produtos disponíveis
    List<Produto> findByDisponivelTrue();

    // Buscar produtos por categoria (case insensitive)
    List<Produto> findByCategoriaContainingIgnoreCase(String categoria);

    // Buscar produtos de um restaurante específico
    List<Produto> findByRestauranteId(Long restauranteId);
}
