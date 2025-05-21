package br.com.fiap.fiap_tc4_srv_produtos.usecase;

import br.com.fiap.fiap_tc4_srv_produtos.domain.Produto;
import br.com.fiap.fiap_tc4_srv_produtos.exception.ProdutoNaoEncontradoException;
import br.com.fiap.fiap_tc4_srv_produtos.gateway.ProdutoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ObterProdutoUseCaseTest {

    private ProdutoGateway gateway;
    private ObterProdutoUseCase useCase;

    @BeforeEach
    void setUp() {
        gateway = mock(ProdutoGateway.class);
        useCase = new ObterProdutoUseCase(gateway);
    }

    @Test
    void deveRetornarProdutoQuandoBuscarPorIdExistente() {
        Long id = 1L;
        Produto produto = Produto.of(id, "Produto", "SKU123", 50.0);
        when(gateway.obterPorId(id)).thenReturn(Optional.of(produto));

        Produto resultado = useCase.obterPorId(id);

        assertEquals(produto, resultado);
        verify(gateway).obterPorId(id);
    }

    @Test
    void deveLancarExcecaoQuandoIdNaoExistir() {
        Long id = 99L;
        when(gateway.obterPorId(id)).thenReturn(Optional.empty());

        assertThrows(ProdutoNaoEncontradoException.class, () -> useCase.obterPorId(id));
        verify(gateway).obterPorId(id);
    }

    @Test
    void deveRetornarProdutoQuandoBuscarPorNomeExistente() {
        String nome = "Produto";
        Produto produto = Produto.of(1L, nome, "SKU123", 50.0);
        when(gateway.obterPorNome(nome)).thenReturn(Optional.of(produto));

        Produto resultado = useCase.obterPorNome(nome);

        assertEquals(produto, resultado);
        verify(gateway).obterPorNome(nome);
    }

    @Test
    void deveLancarExcecaoQuandoNomeNaoExistir() {
        String nome = "Inexistente";
        when(gateway.obterPorNome(nome)).thenReturn(Optional.empty());

        assertThrows(ProdutoNaoEncontradoException.class, () -> useCase.obterPorNome(nome));
        verify(gateway).obterPorNome(nome);
    }
}
