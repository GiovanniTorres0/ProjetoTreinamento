package br.com.mscatalog.controller;

import br.com.mscatalog.dto.CategoriaDto;
import br.com.mscatalog.form.CategoriaForm;
import br.com.mscatalog.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RequestMapping(value = "/v1")
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;


    @PostMapping(value = "/categories")
    public ResponseEntity<CategoriaDto> cadastraCategoria(@RequestBody @Valid CategoriaForm categoriaForm) {
        return categoriaService.salvarCategoria(categoriaForm);
    }

    @GetMapping(value = "/categories")
    public ResponseEntity<CategoriaDto> buscarCategorias() {
        try {
            categoriaService.mostrarCategorias();
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }


    @GetMapping(value = "/categories/{id}/products")
    public ResponseEntity<CategoriaDto> buscarCategoriasPorId(@PathVariable UUID id) {
        return categoriaService.buscarCategoriaId(id);
    }

    @PutMapping(value = "/categories/{id}")
    public ResponseEntity<CategoriaDto> atualizarCategoriaPorId(@PathVariable UUID id, @RequestBody @Valid CategoriaForm categoriaForm) {
        return categoriaService.atualizarCategoria(id, categoriaForm);

    }

    @DeleteMapping(value = "/categories/{id}")
    public ResponseEntity<CategoriaDto> deletarCategoriaPorId(@PathVariable UUID id) {
        return categoriaService.deletarPorId(id);
    }


}



