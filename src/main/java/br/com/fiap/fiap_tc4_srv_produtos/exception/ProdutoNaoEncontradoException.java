package br.com.fiap.fiap_tc4_srv_produtos.exception;

public class ProdutoNaoEncontradoException extends RuntimeException {
    public ProdutoNaoEncontradoException() {
        super("Produto não encontrado.");
    }
}
