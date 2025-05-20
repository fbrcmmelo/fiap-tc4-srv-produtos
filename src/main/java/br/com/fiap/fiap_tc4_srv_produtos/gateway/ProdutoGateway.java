package br.com.fiap.fiap_tc4_srv_produtos.gateway;

import br.com.fiap.fiap_tc4_srv_produtos.domain.Produto;

import java.util.Optional;

public interface ProdutoGateway {
    Optional<Produto> obterPorId(Long id);
    Optional<Produto> obterPorNome(String nome);
    Long criar(Produto produto);
    void atualizar(Produto produto);
    void excluir(Long id);

    Optional<Produto> obterPorSku(String sku);
}