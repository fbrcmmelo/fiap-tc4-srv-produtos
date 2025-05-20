package br.com.fiap.fiap_tc4_srv_produtos.domain;

import lombok.Getter;

@Getter
public class Produto {
    private Long id;
    private String nome;
    private String sku;
    private Double preco;

    public Produto(Long id, String nome, String sku, Double preco) {
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("Nome não pode estar vazio");
        if (preco == null || preco <= 0) throw new IllegalArgumentException("Preço deve ser maior que zero");

        this.id = id;
        this.nome = nome;
        this.sku = sku;
        this.preco = preco;
    }

    // método estático para facilitar criação
    public static Produto of(Long id, String nome, String sku, Double preco) {
        return new Produto(id, nome, sku, preco);
    }
}
