package br.com.mscatalog.service;

import br.com.mscatalog.dto.CategoriaDto;
import br.com.mscatalog.form.CategoriaForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

public interface CategoriaService {
    ResponseEntity<CategoriaDto> salvarCategoria(@RequestBody @Valid CategoriaForm categoriaForm);

    List<CategoriaDto> mostrarCategorias();

    ResponseEntity<CategoriaDto> buscarCategoriaId(@PathVariable UUID id);

    ResponseEntity<CategoriaDto> atualizarCategoria(@PathVariable UUID id, @RequestBody @Valid CategoriaForm categoriaForm);

    ResponseEntity<CategoriaDto> deletarPorId(@PathVariable UUID id);
}
