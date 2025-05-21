package br.com.fiap.fiap_tc4_srv_produtos.controller;

import br.com.fiap.fiap_tc4_srv_produtos.controller.json.ProdutoJson;
import br.com.fiap.fiap_tc4_srv_produtos.domain.Produto;
import br.com.fiap.fiap_tc4_srv_produtos.usecase.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.*;

public class ProdutoControllerTest {

    @InjectMocks
    private ProdutoController controller;

    @Mock
    private CriarProdutoUseCase criarUseCase;

    @Mock
    private AtualizarProdutoUseCase atualizarUseCase;

    @Mock
    private ExcluirProdutoUseCase excluirUseCase;

    @Mock
    private ObterProdutoUseCase obterUseCase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCriarProdutoERetornarId() {
        ProdutoJson json = new ProdutoJson("Produto A", "SKU001", 100.0);
        when(criarUseCase.criar(any())).thenReturn(1L);

        ResponseEntity<Long> response = controller.criar(json);

        assertEquals(CREATED, response.getStatusCode());
        assertEquals(1L, response.getBody());
        verify(criarUseCase).criar(any());
    }

    @Test
    void deveAtualizarProdutoComSucesso() {
        ProdutoJson json = new ProdutoJson("Produto Atualizado", "SKU123", 200.0);

        controller.atualizar(2L, json);

        verify(atualizarUseCase).atualizar(argThat(produto ->
                produto.getId().equals(2L) &&
                        produto.getNome().equals("Produto Atualizado") &&
                        produto.getSku().equals("SKU123") &&
                        produto.getPreco() == 200.0
        ));
    }

    @Test
    void deveExcluirProdutoComSucesso() {
        controller.excluir(3L);
        verify(excluirUseCase).excluir(3L);
    }

    @Test
    void deveObterProdutoPorId() {
        Produto produto = new Produto(1L, "Produto A", "SKU001", 100.0);
        when(obterUseCase.obterPorId(1L)).thenReturn(produto);

        ResponseEntity<ProdutoJson> response = controller.obterPorId(1L);

        assertEquals(OK, response.getStatusCode());
        assertEquals("Produto A", response.getBody().getNome());
        assertEquals("SKU001", response.getBody().getSku());
        assertEquals(100.0, response.getBody().getPreco());
    }

    @Test
    void deveObterProdutoPorNome() {
        Produto produto = new Produto(2L, "Produto B", "SKU002", 150.0);
        when(obterUseCase.obterPorNome("Produto B")).thenReturn(produto);

        ResponseEntity<ProdutoJson> response = controller.obterPorNome("Produto B");

        assertEquals(OK, response.getStatusCode());
        assertEquals("Produto B", response.getBody().getNome());
        assertEquals("SKU002", response.getBody().getSku());
        assertEquals(150.0, response.getBody().getPreco());
    }
}
