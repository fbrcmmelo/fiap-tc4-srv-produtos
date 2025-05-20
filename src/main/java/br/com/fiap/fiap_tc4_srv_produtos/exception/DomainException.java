package br.com.fiap.fiap_tc4_srv_produtos.exception;

public class DomainException extends RuntimeException {
    public DomainException(String message) {
        super(message == null ? "Ocorreu um erro interno" : message);
    }

    public DomainException(String message, Throwable cause) {
        super(message == null ? "Ocorreu um erro interno" : message, cause);
    }
}