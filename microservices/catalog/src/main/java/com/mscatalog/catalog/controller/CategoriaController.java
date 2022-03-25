package com.mscatalog.catalog.controller;

import com.mscatalog.catalog.dto.CategoriaDto;
import com.mscatalog.catalog.form.CategoriaForm;
import com.mscatalog.catalog.services.service.CategoriaService;
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
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;

    @ApiOperation(value = "Cadastra uma Categoria", notes = "Cadastro de uma Categoria de Produto", response = CategoriaController.class)
    @ApiResponses({@ApiResponse(code = 201, message = "Created")})
    @Transactional
    @PostMapping("/categories")
    public ResponseEntity<CategoriaDto> registraCategoria(@RequestBody @Valid CategoriaForm categoriaForm) {
        return categoriaService.cadastraCategoria(categoriaForm);
    }

    @ApiOperation(value = "Busca todos da Categoria", notes = "Busca todas as Categorias de Produtos", response = CategoriaController.class)
    @ApiResponses({@ApiResponse(code = 200, message = "OK")})
    @GetMapping("/categories")
    public List<CategoriaDto> buscarCategorias() {
        return categoriaService.mostraCategorias();
    }

    @ApiOperation(value = "Busca uma Categoria", notes = "Busca uma Categoria de Produto", response = CategoriaController.class)
    @ApiResponses({@ApiResponse(code = 200, message = "OK")})
    @GetMapping("/categories/{id}/products")
    public ResponseEntity<CategoriaDto> mostraCategoria(@PathVariable Integer id) {
        return categoriaService.buscaCategoria(id);
    }

    @ApiOperation(value = "Atualiza uma Categoria", notes = "Busca uma Categoria de Produto e Atualiza", response = CategoriaController.class)
    @ApiResponses({@ApiResponse(code = 202, message = "ACCEPTED")})
    @Transactional
    @PutMapping("/categories/{id}")
    public ResponseEntity<CategoriaDto> atualizarCategoria(@RequestBody @Valid CategoriaForm categoriaForm, @PathVariable Integer id) {
        return categoriaService.atualizaCategoria(categoriaForm, id);
    }

    @ApiOperation(value = "Deleta uma Categoria", notes = "Busca uma Categoria de Produto e Deleta", response = CategoriaController.class)
    @ApiResponses({@ApiResponse(code = 200, message = "OK")})
    @DeleteMapping("/categories/{id}")
    public ResponseEntity<CategoriaDto> deletarCategoria(@PathVariable Integer id) {
        return categoriaService.deletarCategoria(id);
    }


}
