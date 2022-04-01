package com.msbffshop.bffshop.controller;

import com.msbffshop.bffshop.services.endpointservice.CatalogMicroService;
import com.mscatalog.catalog.dto.CategoriaDto;
import com.mscatalog.catalog.dto.ProdutoDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class BffCatalogController {

    @Autowired
    CatalogMicroService catalogMicroService;

    @ApiOperation(value = "Busca um Produto", notes = "Busca um Produto", response = BffCatalogController.class)
    @ApiResponses({@ApiResponse(code = 200, message = "OK")})
    @GetMapping("/products/{id}")
    public ResponseEntity<ProdutoDto> buscaProdutoPorId(@PathVariable(value = "id") Integer id){
        return  catalogMicroService.buscarProdutoPorId(id);
    }

    @ApiOperation(value = "Busca uma Categoria", notes = "Busca uma Categoria de Produto", response = BffCatalogController.class)
    @ApiResponses({@ApiResponse(code = 200, message = "OK")})
    @GetMapping("/categories/{id}/products")
    public ResponseEntity<CategoriaDto> buscarCategoriaPorId(@PathVariable(value = "id") Integer id){
        return catalogMicroService.buscarCategoriaPorId(id);
    }

}
