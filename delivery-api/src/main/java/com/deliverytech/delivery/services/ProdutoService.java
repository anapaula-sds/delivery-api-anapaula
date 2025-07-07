package com.deliverytech.delivery.services;


import com.deliverytech.delivery.dto.ProdutoRequestDTO;
import com.deliverytech.delivery.dto.ProdutoResponseDTO;

import java.math.BigDecimal;
import java.util.List;

public interface ProdutoService {

    ProdutoResponseDTO cadastrar(ProdutoRequestDTO dto);

    ProdutoResponseDTO buscarPorId(Long id);

    ProdutoResponseDTO atualizar(Long id, ProdutoRequestDTO dto);

    ProdutoResponseDTO ativarDesativarProduto(Long id);

    ProdutoResponseDTO buscarPorNome(String nome);

    List<ProdutoResponseDTO>  buscarPorRestaurante(Long restauranteId);

    List<ProdutoResponseDTO> buscarPorCategoria(String categoria);

    List<ProdutoResponseDTO> buscarPorPreco(BigDecimal precoMinimo, BigDecimal precoMaximo);

    List<ProdutoResponseDTO> buscarTodosProdutos();

    List<ProdutoResponseDTO> buscarPorPrecoMenorOuIgual(BigDecimal valor);
}