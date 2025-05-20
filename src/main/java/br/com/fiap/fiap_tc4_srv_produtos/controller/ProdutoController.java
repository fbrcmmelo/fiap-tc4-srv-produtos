package br.com.fiap.fiap_tc4_srv_produtos.controller;


import br.com.fiap.fiap_tc4_srv_produtos.controller.json.ProdutoJson;
import br.com.fiap.fiap_tc4_srv_produtos.domain.Produto;
import br.com.fiap.fiap_tc4_srv_produtos.usecase.AtualizarProdutoUseCase;
import br.com.fiap.fiap_tc4_srv_produtos.usecase.CriarProdutoUseCase;
import br.com.fiap.fiap_tc4_srv_produtos.usecase.ExcluirProdutoUseCase;
import br.com.fiap.fiap_tc4_srv_produtos.usecase.ObterProdutoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final CriarProdutoUseCase criarUseCase;
    private final AtualizarProdutoUseCase atualizarUseCase;
    private final ExcluirProdutoUseCase excluirUseCase;
    private final ObterProdutoUseCase obterUseCase;

    @PostMapping
    @ResponseStatus(CREATED)
    public ResponseEntity<Long> criar(@RequestBody ProdutoJson json) {
        Long id = criarUseCase.criar(mapToDomain(json));
        return ResponseEntity.status(CREATED).body(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void atualizar(@PathVariable Long id, @RequestBody ProdutoJson json) {
        Produto produto = mapToDomain(json);
        produto = new Produto(id, produto.getNome(), produto.getSku(), produto.getPreco());
        atualizarUseCase.atualizar(produto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        excluirUseCase.excluir(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoJson> obterPorId(@PathVariable Long id) {
        Produto produto = obterUseCase.obterPorId(id);
        return ResponseEntity.ok(mapToJson(produto));
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<ProdutoJson> obterPorNome(@PathVariable String nome) {
        Produto produto = obterUseCase.obterPorNome(nome);
        return ResponseEntity.ok(mapToJson(produto));
    }

    private Produto mapToDomain(ProdutoJson json) {
        return new Produto(null, json.getNome(), json.getSku(), json.getPreco());
    }

    private ProdutoJson mapToJson(Produto produto) {
        return ProdutoJson.builder()
                .nome(produto.getNome())
                .sku(produto.getSku())
                .preco(produto.getPreco())
                .build();
    }
}