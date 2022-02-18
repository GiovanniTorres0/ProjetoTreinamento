package br.com.mscatalog.controller;

import br.com.mscatalog.dto.ProdutoDto;
import br.com.mscatalog.form.ProdutoForm;
import br.com.mscatalog.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(value = "/v1")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;


    @PostMapping(value ="/products")
    public ResponseEntity<ProdutoDto> cadastroProduto(@RequestBody @Valid ProdutoForm produtoForm) {
        return produtoService.salvarProduto(produtoForm);
    }

    @GetMapping(value ="/products")
    public ResponseEntity<ProdutoDto> listarProdutos() {
        try {
            produtoService.ListarProdutos();
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(value ="/products/{id}")
    public ResponseEntity<ProdutoDto> listarProdutoId(@PathVariable UUID id) {
        return produtoService.buscarPorId(id);
    }

    @PutMapping(value ="/products/{id}")
    public ResponseEntity<ProdutoDto> atualizarProduto(@PathVariable UUID id, @RequestBody @Valid ProdutoForm produtoForm) {
        return produtoService.atualizarPorId(id, produtoForm);
    }

    @DeleteMapping(value ="/products/{id}")
    public ResponseEntity<ProdutoDto> deletarProduto(@PathVariable UUID id) {
        return produtoService.deletarPorId(id);
    }


}