package com.mscatalog.catalog.services.service;


import com.mscatalog.catalog.dto.ProdutoDto;
import com.mscatalog.catalog.dto.ProdutosVariadosDto;
import com.mscatalog.catalog.form.ProdutoForm;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@Service
public interface ProdutoService {


    @Transactional
    ResponseEntity<ProdutoDto> registraProduto(@RequestBody @Valid ProdutoForm produtoForm) throws Exception;

    List<ProdutosVariadosDto> buscaTodosProdutos();

    ResponseEntity<ProdutoDto> buscaPorId(@PathVariable Integer id);

    @Transactional
    ResponseEntity<ProdutoDto> atualizarPorId(@PathVariable Integer id, @RequestBody @Valid ProdutoForm produtoForm);

    @Transactional
    ResponseEntity<ProdutoDto> deletarPorId(@PathVariable Integer id);


    List<ProdutosVariadosDto> retorna();

    double valor();
}
