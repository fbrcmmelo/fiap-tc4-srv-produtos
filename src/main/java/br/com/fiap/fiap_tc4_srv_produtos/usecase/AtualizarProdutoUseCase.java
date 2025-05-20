package br.com.fiap.fiap_tc4_srv_produtos.usecase;

import br.com.fiap.fiap_tc4_srv_produtos.domain.Produto;
import br.com.fiap.fiap_tc4_srv_produtos.exception.ProdutoNaoEncontradoException;
import br.com.fiap.fiap_tc4_srv_produtos.gateway.ProdutoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AtualizarProdutoUseCase {

    private final ProdutoGateway gateway;

    public void atualizar(Produto produto) {
        if (gateway.obterPorId(produto.getId()).isEmpty()) {
            throw new ProdutoNaoEncontradoException();
        }
        gateway.atualizar(produto);
    }
}
