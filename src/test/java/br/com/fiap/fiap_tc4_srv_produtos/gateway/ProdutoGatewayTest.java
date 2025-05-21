package br.com.fiap.fiap_tc4_srv_produtos.gateway;

import br.com.fiap.fiap_tc4_srv_produtos.domain.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProdutoGatewayTest {

    private ProdutoGateway gateway;

    @BeforeEach
    void setup() {
        gateway = mock(ProdutoGateway.class);
    }

    @Test
    void deveCriarProduto() {
        Produto produto = Produto.of(null, "Produto Teste", "SKU123", 10.0);
        Long idMock = 1L;

        when(gateway.criar(produto)).thenReturn(idMock);
        when(gateway.obterPorId(idMock)).thenReturn(Optional.of(Produto.of(idMock, "Produto Teste", "SKU123", 10.0)));

        Long id = gateway.criar(produto);

        assertNotNull(id);
        Optional<Produto> produtoSalvo = gateway.obterPorId(id);
        assertTrue(produtoSalvo.isPresent());
        assertEquals("Produto Teste", produtoSalvo.get().getNome());

        verify(gateway).criar(produto);
        verify(gateway).obterPorId(idMock);
    }

    @Test
    void deveObterProdutoPorNome() {
        Produto produto = Produto.of(null, "Produto Nome", "SKU124", 15.0);
        Long idMock = 2L;

        when(gateway.criar(produto)).thenReturn(idMock);
        when(gateway.obterPorNome("Produto Nome")).thenReturn(Optional.of(Produto.of(idMock, "Produto Nome", "SKU124", 15.0)));

        Long id = gateway.criar(produto);
        Optional<Produto> produtoBuscado = gateway.obterPorNome("Produto Nome");

        assertTrue(produtoBuscado.isPresent());
        assertEquals(id, produtoBuscado.get().getId());

        verify(gateway).criar(produto);
        verify(gateway).obterPorNome("Produto Nome");
    }

    @Test
    void deveAtualizarProduto() {
        Long idMock = 3L;
        Produto produtoOriginal = Produto.of(idMock, "Produto Atualizar", "SKU125", 20.0);
        Produto produtoAtualizado = Produto.of(idMock, "Produto Atualizado", "SKU125", 25.0);

        when(gateway.criar(any())).thenReturn(idMock);
        doNothing().when(gateway).atualizar(produtoAtualizado);
        when(gateway.obterPorId(idMock)).thenReturn(Optional.of(produtoAtualizado));

        Long id = gateway.criar(produtoOriginal);
        gateway.atualizar(produtoAtualizado);

        Optional<Produto> produtoBuscado = gateway.obterPorId(id);
        assertTrue(produtoBuscado.isPresent());
        assertEquals("Produto Atualizado", produtoBuscado.get().getNome());
        assertEquals(25.0, produtoBuscado.get().getPreco());

        verify(gateway).criar(produtoOriginal);
        verify(gateway).atualizar(produtoAtualizado);
        verify(gateway).obterPorId(idMock);
    }

    @Test
    void deveExcluirProduto() {
        Long idMock = 4L;

        when(gateway.criar(any())).thenReturn(idMock);
        doNothing().when(gateway).excluir(idMock);
        when(gateway.obterPorId(idMock)).thenReturn(Optional.empty());

        Long id = gateway.criar(Produto.of(null, "Produto Excluir", "SKU126", 30.0));
        gateway.excluir(id);

        Optional<Produto> produtoBuscado = gateway.obterPorId(id);
        assertTrue(produtoBuscado.isEmpty());

        verify(gateway).criar(any());
        verify(gateway).excluir(idMock);
        verify(gateway).obterPorId(idMock);
    }
}
