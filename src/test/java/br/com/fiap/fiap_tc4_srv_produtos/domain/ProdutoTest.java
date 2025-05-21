package br.com.fiap.fiap_tc4_srv_produtos.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProdutoTest {

    @Test
    void deveCriarProdutoValido() {
        Produto produto = new Produto(1L, "Produto Teste", "SKU123", 99.9);

        assertEquals(1L, produto.getId());
        assertEquals("Produto Teste", produto.getNome());
        assertEquals("SKU123", produto.getSku());
        assertEquals(99.9, produto.getPreco());
    }

    @Test
    void deveLancarExcecaoQuandoNomeForNulo() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Produto(1L, null, "SKU001", 100.0);
        });

        assertEquals("Nome não pode estar vazio", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoNomeForEmBranco() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Produto(1L, "   ", "SKU001", 100.0);
        });

        assertEquals("Nome não pode estar vazio", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoPrecoForNulo() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Produto(1L, "Produto A", "SKU001", null);
        });

        assertEquals("Preço deve ser maior que zero", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoPrecoForZeroOuNegativo() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Produto(1L, "Produto A", "SKU001", 0.0);
        });

        assertEquals("Preço deve ser maior que zero", exception.getMessage());
    }
}
