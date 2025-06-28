package com.deliverytech.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.deliverytech.delivery.entity.Restaurante;

import java.util.List;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

    // Buscar restaurantes ativos
    List<Restaurante> findByAtivoTrue();

    // Buscar restaurantes por nome parcial (ignorando maiúsculas/minúsculas)
    List<Restaurante> findByNomeContainingIgnoreCase(String nome);

    // Buscar restaurante por email (se tiver)
    // Optional<Restaurante> findByEmail(String email);

    // Buscar por cidade (usando parte do endereço)
    @Query(value = "SELECT * FROM restaurantes WHERE endereco LIKE %:cidade% AND ativo = true", nativeQuery = true)
    List<Restaurante> findByCidade(String cidade);

    // Contar restaurantes ativos
    @Query("SELECT COUNT(r) FROM Restaurante r WHERE r.ativo = true")
    Long countAtivos();
}
