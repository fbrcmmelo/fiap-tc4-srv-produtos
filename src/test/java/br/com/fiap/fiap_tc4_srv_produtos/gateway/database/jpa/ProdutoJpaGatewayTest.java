package br.com.fiap.fiap_tc4_srv_produtos.gateway.database.jpa;

import br.com.fiap.fiap_tc4_srv_produtos.domain.Produto;
import br.com.fiap.fiap_tc4_srv_produtos.gateway.database.entity.ProdutoEntity;
import br.com.fiap.fiap_tc4_srv_produtos.gateway.database.jpa.repository.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProdutoJpaGatewayTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ProdutoJpaGateway produtoJpaGateway;

    @Test
    void obterPorIdShouldReturnProdutoWhenIdExists() {
        ProdutoEntity entity = ProdutoEntity.builder()
                .id(1L)
                .nome("Produto A")
                .sku("SKU123")
                .price(100.0)
                .build();
        when(produtoRepository.findById(1L)).thenReturn(Optional.of(entity));

        Optional<Produto> result = produtoJpaGateway.obterPorId(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        assertEquals("Produto A", result.get().getNome());
        assertEquals("SKU123", result.get().getSku());
        assertEquals(100.0, result.get().getPreco());
    }

    @Test
    void obterPorIdShouldReturnEmptyWhenIdDoesNotExist() {
        when(produtoRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Produto> result = produtoJpaGateway.obterPorId(1L);

        assertFalse(result.isPresent());
    }

    @Test
    void criarShouldReturnIdWhenProdutoIsSaved() {
        Produto produto = new Produto(null, "Produto B", "SKU456", 200.0);
        ProdutoEntity savedEntity = ProdutoEntity.builder()
                .id(2L)
                .nome("Produto B")
                .sku("SKU456")
                .price(200.0)
                .build();
        when(produtoRepository.save(any(ProdutoEntity.class))).thenReturn(savedEntity);

        Long result = produtoJpaGateway.criar(produto);

        assertEquals(2L, result);
    }

    @Test
    void atualizarShouldSaveProdutoEntity() {
        Produto produto = new Produto(3L, "Produto C", "SKU789", 300.0);
        ProdutoEntity entity = ProdutoEntity.builder()
                .id(3L)
                .nome("Produto C")
                .sku("SKU789")
                .price(300.0)
                .build();
        when(produtoRepository.save(any(ProdutoEntity.class))).thenReturn(entity);

        produtoJpaGateway.atualizar(produto);

        verify(produtoRepository).save(any(ProdutoEntity.class));
    }

    @Test
    void excluirShouldDeleteProdutoById() {
        Long id = 4L;

        produtoJpaGateway.excluir(id);

        verify(produtoRepository).deleteById(id);
    }
}