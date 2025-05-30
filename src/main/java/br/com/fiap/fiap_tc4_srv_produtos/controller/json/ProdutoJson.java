package br.com.fiap.fiap_tc4_srv_produtos.controller.json;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoJson {
    private String nome;
    private String sku;
    private Double preco;
}