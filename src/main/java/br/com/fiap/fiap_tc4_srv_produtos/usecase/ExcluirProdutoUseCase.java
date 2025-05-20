package br.com.fiap.fiap_tc4_srv_produtos.usecase;

import br.com.fiap.fiap_tc4_srv_produtos.exception.ProdutoNaoEncontradoException;
import br.com.fiap.fiap_tc4_srv_produtos.gateway.ProdutoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExcluirProdutoUseCase {
    private final ProdutoGateway gateway;

    public void excluir(Long id) {
        if (gateway.obterPorId(id).isEmpty()) {
            throw new ProdutoNaoEncontradoException();
        }
        gateway.excluir(id);
    }
}
