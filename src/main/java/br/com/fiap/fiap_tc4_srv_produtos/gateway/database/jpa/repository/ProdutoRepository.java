package br.com.fiap.fiap_tc4_srv_produtos.gateway.database.jpa.repository;

import br.com.fiap.fiap_tc4_srv_produtos.gateway.database.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {
    Optional<ProdutoEntity> findByNome(String nome);
    Optional<ProdutoEntity> findBySku(String sku);
}

