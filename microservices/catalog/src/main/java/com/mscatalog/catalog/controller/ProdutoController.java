package com.mscatalog.catalog.controller;

import com.mscatalog.catalog.dto.ProdutoDto;
import com.mscatalog.catalog.dto.ProdutosVariadosDto;
import com.mscatalog.catalog.form.ProdutoForm;
import com.mscatalog.catalog.services.service.ProdutoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1")
public class ProdutoController {


    @Autowired
    ProdutoService produtoService;

    @ApiOperation(value = "Cadastra um Produto", notes = "Cadastro de um Produto", response = ProdutoController.class)
    @ApiResponses({@ApiResponse(code = 201, message = "Created")})
    @Transactional
    @PostMapping("/products")
    public ResponseEntity<ProdutoDto> registraProduto(@RequestBody @Valid ProdutoForm produtoForm) throws Exception {
        return produtoService.registraProduto(produtoForm);
    }

    @ApiOperation(value = "Busca todos os Produtos", notes = "Busca todas os Produtos", response = ProdutoController.class)
    @ApiResponses({@ApiResponse(code = 200, message = "OK")})
    @GetMapping("/products")
    public List<ProdutosVariadosDto> buscaProdutos() {
        return produtoService.buscaTodosProdutos();
    }

    @ApiOperation(value = "Busca um Produto", notes = "Busca um Produto", response = ProdutoController.class)
    @ApiResponses({@ApiResponse(code = 200, message = "OK")})
    @GetMapping("/product/{id}")
    public ResponseEntity<ProdutoDto> buscaPorId(@PathVariable Integer id) {
        return produtoService.buscaPorId(id);
    }

    @ApiOperation(value = "Atualiza um Produto", notes = "Busca e atualiza um Produto", response = ProdutoController.class)
    @ApiResponses({@ApiResponse(code = 202, message = "ACCEPTED")})
    @Transactional
    @PutMapping("/products/{id}")
    public ResponseEntity<ProdutoDto> atualizarPorId(@PathVariable Integer id, @RequestBody @Valid ProdutoForm produtoForm) {
        return produtoService.atualizarPorId(id, produtoForm);
    }

    @ApiOperation(value = "Deleta um Produto", notes = "Busca um Produto e Deleta", response = ProdutoController.class)
    @ApiResponses({@ApiResponse(code = 200, message = "OK")})
    @DeleteMapping("/products/{id}")
    public ResponseEntity<ProdutoDto> deletarPorId(@PathVariable Integer id) {
        return produtoService.deletarPorId(id);
    }


    @GetMapping("/products/{id}")
    public ResponseEntity<List<ProdutosVariadosDto>> buscaProdutoVariadoPorId(@PathVariable Integer id){
        return produtoService.buscaProdutoVariadoPorId(id);
    }

}
