package br.com.fiap.fiap_tc4_srv_produtos.usecase;

import br.com.fiap.fiap_tc4_srv_produtos.domain.Produto;
import br.com.fiap.fiap_tc4_srv_produtos.exception.ProdutoExistenteException;
import br.com.fiap.fiap_tc4_srv_produtos.gateway.ProdutoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CriarProdutoUseCase {

    private final ProdutoGateway gateway;

    public Long criar(Produto produto) {
        if (gateway.obterPorNome(produto.getNome()).isPresent()) {
            throw new ProdutoExistenteException();
        }
        return gateway.criar(produto);
    }
}