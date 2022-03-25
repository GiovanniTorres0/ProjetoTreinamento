package com.mscatalog.catalog.services.serviceimp;


import com.mscatalog.catalog.dto.CategoriaDto;
import com.mscatalog.catalog.entity.Categoria;
import com.mscatalog.catalog.form.CategoriaForm;
import com.mscatalog.catalog.repository.CategoriaRepository;
import com.mscatalog.catalog.services.sequence.SequenceGeneratorService;
import com.mscatalog.catalog.services.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.mscatalog.catalog.entity.Produto.SEQUENCE_NAME;


@Service
public class CategoriaServiceImp implements CategoriaService {


    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    SequenceGeneratorService sequenceGeneratorService;


    @Transactional
    @Override
    public ResponseEntity<CategoriaDto> cadastraCategoria(@RequestBody @Valid CategoriaForm categoriaForm) {
        Categoria categoria = categoriaForm.converter(categoriaForm);
        if (categoria != null) {
            categoria.setId(sequenceGeneratorService.getSequenceNumber(SEQUENCE_NAME));
            categoria = categoriaRepository.save(categoria);
            return new ResponseEntity<CategoriaDto>(new CategoriaDto(categoria), HttpStatus.CREATED);
        }
        return ResponseEntity.badRequest().build();
    }

    @Override
    public List<CategoriaDto> mostraCategorias() {
        List<Categoria> categorias = categoriaRepository.findAll();
        if (categorias != null) {
            List<CategoriaDto> converter = CategoriaDto.converter(categorias);
            return converter;
        }
        throw new NoSuchElementException();
    }

    @Override
    public ResponseEntity<CategoriaDto> buscaCategoria(@PathVariable Integer id) {
        Optional<Categoria> optional = categoriaRepository.findById(id);
        if (optional.isPresent()) {
            return new ResponseEntity<CategoriaDto>(new CategoriaDto(optional.get()), HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional
    @Override
    public ResponseEntity<CategoriaDto> atualizaCategoria(@RequestBody @Valid CategoriaForm categoriaForm, @PathVariable Integer id) {
        Optional<Categoria> optional = categoriaRepository.findById(id);
        if (optional.isPresent()) {
            Categoria categoria = categoriaForm.converter(categoriaForm);
            categoria.setId(id);
            return new ResponseEntity<CategoriaDto>(new CategoriaDto(categoriaRepository.save(categoria)), HttpStatus.ACCEPTED);
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<CategoriaDto> deletarCategoria(@PathVariable Integer id) {
        Optional<Categoria> optional = categoriaRepository.findById(id);
        if (optional.isPresent()) {
            categoriaRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }


}
