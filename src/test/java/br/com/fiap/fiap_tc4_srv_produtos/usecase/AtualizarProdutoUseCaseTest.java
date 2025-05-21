package br.com.fiap.fiap_tc4_srv_produtos.usecase;


import br.com.fiap.fiap_tc4_srv_produtos.domain.Produto;
import br.com.fiap.fiap_tc4_srv_produtos.exception.ProdutoNaoEncontradoException;
import br.com.fiap.fiap_tc4_srv_produtos.gateway.ProdutoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class AtualizarProdutoUseCaseTest {

    private ProdutoGateway gateway;
    private AtualizarProdutoUseCase useCase;

    @BeforeEach
    void setup() {
        gateway = mock(ProdutoGateway.class);
        useCase = new AtualizarProdutoUseCase(gateway);
    }

    @Test
    void deveAtualizarProdutoQuandoProdutoExistir() {
        // Arrange
        Produto produto = Produto.of(1L, "Produto Atualizado", "SKU-123", 150.0);

        when(gateway.obterPorId(1L)).thenReturn(Optional.of(produto));

        // Act
        useCase.atualizar(produto);

        // Assert
        verify(gateway).obterPorId(1L);
        verify(gateway).atualizar(produto);
    }

    @Test
    void deveLancarExcecaoQuandoProdutoNaoExistir() {
        // Arrange
        Produto produto = Produto.of(1L, "Inexistente", "SKU-000", 99.9);

        when(gateway.obterPorId(1L)).thenReturn(Optional.empty());

        // Act & Assert
        ProdutoNaoEncontradoException exception = assertThrows(ProdutoNaoEncontradoException.class, () -> {
            useCase.atualizar(produto);
        });

        assertNotNull(exception);
        verify(gateway).obterPorId(1L);
        verify(gateway, never()).atualizar(any());
    }
}