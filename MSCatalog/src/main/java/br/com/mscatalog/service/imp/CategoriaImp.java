package br.com.mscatalog.service.imp;

import br.com.mscatalog.dto.CategoriaDto;
import br.com.mscatalog.form.CategoriaForm;
import br.com.mscatalog.models.Categoria;
import br.com.mscatalog.repository.CategoriaRepository;
import br.com.mscatalog.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class CategoriaImp implements CategoriaService {


    @Autowired
    CategoriaRepository categoriaRepository;


    @Override
    public ResponseEntity<CategoriaDto> salvarCategoria(@RequestBody @Valid CategoriaForm categoriaForm) {
        Categoria categoria = categoriaForm.converter();
        if (categoria != null) {
            categoriaRepository.save(categoria);
            return new ResponseEntity<CategoriaDto>(new CategoriaDto(categoria), HttpStatus.CREATED);
        }
        return new ResponseEntity<CategoriaDto>(HttpStatus.BAD_REQUEST);
    }


    @Override
    public List<CategoriaDto> mostrarCategorias() {
        List<Categoria> categorias = categoriaRepository.findAll();
        try {
            if (categorias != null) {
                return CategoriaDto.converter(categorias);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public ResponseEntity<CategoriaDto> buscarCategoriaId(@PathVariable UUID id) {
        Optional<Categoria> optional = categoriaRepository.findById(id);
        if (optional.isPresent()) {

            return new ResponseEntity<CategoriaDto>(new CategoriaDto(optional.get()), HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @Override
    public ResponseEntity<CategoriaDto> atualizarCategoria(@PathVariable UUID id, @RequestBody @Valid CategoriaForm categoriaForm) {
        Optional<Categoria> optional = categoriaRepository.findById(id);
        if (optional.isPresent()) {
            Categoria categoria = categoriaForm.converter();
            categoriaRepository.save(categoria);
            return new ResponseEntity<CategoriaDto>(new CategoriaDto(categoria), HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @Override
    public ResponseEntity<CategoriaDto> deletarPorId(@PathVariable UUID id) {
        Optional<Categoria> optional = categoriaRepository.findById(id);
        if (optional.isPresent()) {
            categoriaRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}


