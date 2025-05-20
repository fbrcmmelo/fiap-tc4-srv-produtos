package br.com.fiap.fiap_tc4_srv_produtos.gateway.database.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "produto")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String sku;
    private Double price;
}
