package com.deliverytech.delivery.services;


import com.deliverytech.delivery.dto.RestauranteRequestDTO;
import com.deliverytech.delivery.dto.RestauranteResponseDTO;
import com.deliverytech.delivery.projection.RelatorioVendas;

import java.math.BigDecimal;
import java.util.List;

public interface RestauranteService {

    RestauranteResponseDTO cadastrar(RestauranteRequestDTO dto);

    RestauranteResponseDTO buscarPorId(Long id);

    RestauranteResponseDTO atualizar(Long id, RestauranteRequestDTO dto);

    RestauranteResponseDTO ativarDesativarRestaurante(Long id);

    RestauranteResponseDTO buscarPorNome(String nome);

    List<RestauranteResponseDTO> buscarPorCategoria(String categoria);

    List<RestauranteResponseDTO> buscarPorPreco(BigDecimal precoMinimo, BigDecimal precoMaximo);

    List<RestauranteResponseDTO> listarAtivos();

    List<RestauranteResponseDTO> listarTop5PorNome();

    List<RelatorioVendas> relatorioVendasPorRestaurante();

    List<RestauranteResponseDTO> buscarPorTaxaEntrega(BigDecimal taxaEntrega);

    RestauranteResponseDTO inativarRestaurante(Long id);
}