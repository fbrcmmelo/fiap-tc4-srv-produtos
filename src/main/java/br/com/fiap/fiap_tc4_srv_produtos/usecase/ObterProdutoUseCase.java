package br.com.fiap.fiap_tc4_srv_produtos.usecase;

import br.com.fiap.fiap_tc4_srv_produtos.domain.Produto;
import br.com.fiap.fiap_tc4_srv_produtos.exception.ProdutoNaoEncontradoException;
import br.com.fiap.fiap_tc4_srv_produtos.gateway.ProdutoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ObterProdutoUseCase {
    private final ProdutoGateway gateway;

    public Produto obterPorId(Long id) {
        return gateway.obterPorId(id)
                .orElseThrow(ProdutoNaoEncontradoException::new);
    }

    public Produto obterPorNome(String nome) {
        return gateway.obterPorNome(nome)
                .orElseThrow(ProdutoNaoEncontradoException::new);
    }
}
