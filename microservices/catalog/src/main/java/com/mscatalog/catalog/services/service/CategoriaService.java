package com.mscatalog.catalog.services.service;


import com.mscatalog.catalog.dto.CategoriaDto;
import com.mscatalog.catalog.form.CategoriaForm;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@Service
public interface CategoriaService {

    @Transactional
    ResponseEntity<CategoriaDto> cadastraCategoria(@RequestBody @Valid CategoriaForm categoriaForm);

    List<CategoriaDto> mostraCategorias();

    ResponseEntity<CategoriaDto> buscaCategoria(@PathVariable Integer id);

    @Transactional
    ResponseEntity<CategoriaDto> atualizaCategoria(@RequestBody @Valid CategoriaForm categoriaForm, @PathVariable Integer id);

    ResponseEntity<CategoriaDto> deletarCategoria(@PathVariable Integer id);
}
