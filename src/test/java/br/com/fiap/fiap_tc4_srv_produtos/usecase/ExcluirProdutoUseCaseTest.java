package br.com.fiap.fiap_tc4_srv_produtos.usecase;


import br.com.fiap.fiap_tc4_srv_produtos.domain.Produto;
import br.com.fiap.fiap_tc4_srv_produtos.exception.ProdutoNaoEncontradoException;
import br.com.fiap.fiap_tc4_srv_produtos.gateway.ProdutoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExcluirProdutoUseCaseTest {

    private ProdutoGateway gateway;
    private ExcluirProdutoUseCase useCase;

    @BeforeEach
    void setUp() {
        gateway = mock(ProdutoGateway.class);
        useCase = new ExcluirProdutoUseCase(gateway);
    }

    @Test
    void deveExcluirProdutoQuandoExistir() {
        // Arrange
        Long id = 1L;
        Produto produto = Produto.of(id, "Produto", "SKU001", 100.0);
        when(gateway.obterPorId(id)).thenReturn(Optional.of(produto));

        // Act
        useCase.excluir(id);

        // Assert
        verify(gateway).obterPorId(id);
        verify(gateway).excluir(id);
    }

    @Test
    void deveLancarExcecaoQuandoProdutoNaoExistir() {
        // Arrange
        Long id = 2L;
        when(gateway.obterPorId(id)).thenReturn(Optional.empty());

        // Act & Assert
        ProdutoNaoEncontradoException exception = assertThrows(ProdutoNaoEncontradoException.class, () -> {
            useCase.excluir(id);
        });

        assertNotNull(exception);
        verify(gateway).obterPorId(id);
        verify(gateway, never()).excluir(anyLong());
    }
}
