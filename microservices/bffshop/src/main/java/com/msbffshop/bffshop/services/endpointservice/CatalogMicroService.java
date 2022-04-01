package com.msbffshop.bffshop.services.endpointservice;

import com.mscatalog.catalog.dto.CategoriaDto;
import com.mscatalog.catalog.dto.ProdutoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "catalog", url = "http://localhost:8082/v1")
public interface CatalogMicroService {

    @RequestMapping(method = RequestMethod.GET, value = "/products/{id}")
    ResponseEntity<ProdutoDto> buscarProdutoPorId(@PathVariable(value = "id") Integer id);

    @RequestMapping(method = RequestMethod.PUT, value = "/categories/{id}/products")
    ResponseEntity<CategoriaDto> buscarCategoriaPorId(@PathVariable(value = "id") Integer id);


}
