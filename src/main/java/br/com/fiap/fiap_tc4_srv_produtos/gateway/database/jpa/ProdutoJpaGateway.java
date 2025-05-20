package br.com.fiap.fiap_tc4_srv_produtos.gateway.database.jpa;

import br.com.fiap.fiap_tc4_srv_produtos.domain.Produto;
import br.com.fiap.fiap_tc4_srv_produtos.gateway.ProdutoGateway;
import br.com.fiap.fiap_tc4_srv_produtos.gateway.database.entity.ProdutoEntity;
import br.com.fiap.fiap_tc4_srv_produtos.gateway.database.jpa.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProdutoJpaGateway implements ProdutoGateway {

    private final ProdutoRepository produtoRepository;

    private Produto toDomain(ProdutoEntity entity) {
        return new Produto(entity.getId(),
                entity.getNome(),
                entity.getSku(),
                entity.getPrice());
    }

    private ProdutoEntity toEntity(Produto produto) {
        return ProdutoEntity.builder()
                .id(produto.getId())
                .nome(produto.getNome())
                .sku(produto.getSku())
                .price(produto.getPreco())
                .build();
    }

    @Override
    public Optional<Produto> obterPorId(Long id) {
        return produtoRepository.findById(id).map(this::toDomain);
    }

    @Override
    public Optional<Produto> obterPorNome(String nome) {
        return produtoRepository.findByNome(nome).map(this::toDomain);
    }

    @Override
    public Optional<Produto> obterPorSku(String sku) {
        return produtoRepository.findBySku(sku).map(this::toDomain);
    }

    @Override
    public Long criar(Produto produto) {
        ProdutoEntity entity = produtoRepository.save(toEntity(produto));
        return entity.getId();
    }

    @Override
    public void atualizar(Produto produto) {
        produtoRepository.save(toEntity(produto));
    }

    @Override
    public void excluir(Long id) {
        produtoRepository.deleteById(id);
    }
}