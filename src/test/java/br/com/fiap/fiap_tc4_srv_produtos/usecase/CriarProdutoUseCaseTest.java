package br.com.fiap.fiap_tc4_srv_produtos.usecase;

import br.com.fiap.fiap_tc4_srv_produtos.domain.Produto;
import br.com.fiap.fiap_tc4_srv_produtos.exception.ProdutoExistenteException;
import br.com.fiap.fiap_tc4_srv_produtos.gateway.ProdutoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class CriarProdutoUseCaseTest {

    private ProdutoGateway gateway;
    private CriarProdutoUseCase useCase;

    @BeforeEach
    void setUp() {
        gateway = mock(ProdutoGateway.class);
        useCase = new CriarProdutoUseCase(gateway);
    }

    @Test
    void deveCriarProdutoQuandoNaoExistirComMesmoNome() {
        // Arrange
        Produto produto = Produto.of(null, "Produto Novo", "SKU123", 199.90);
        when(gateway.obterPorNome("Produto Novo")).thenReturn(Optional.empty());
        when(gateway.criar(produto)).thenReturn(1L);

        // Act
        Long id = useCase.criar(produto);

        // Assert
        assertEquals(1L, id);
        verify(gateway).obterPorNome("Produto Novo");
        verify(gateway).criar(produto);
    }

    @Test
    void deveLancarExcecaoQuandoProdutoComMesmoNomeJaExiste() {
        // Arrange
        Produto produto = Produto.of(null, "Produto Existente", "SKU000", 150.00);
        when(gateway.obterPorNome("Produto Existente")).thenReturn(Optional.of(produto));

        // Act & Assert
        ProdutoExistenteException exception = assertThrows(ProdutoExistenteException.class, () -> {
            useCase.criar(produto);
        });

        assertNotNull(exception);
        verify(gateway).obterPorNome("Produto Existente");
        verify(gateway, never()).criar(any());
    }
}