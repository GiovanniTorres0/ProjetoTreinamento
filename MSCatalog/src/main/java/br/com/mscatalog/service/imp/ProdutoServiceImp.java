package br.com.mscatalog.service.imp;

import br.com.mscatalog.dto.ProdutoDto;
import br.com.mscatalog.form.ProdutoForm;
import br.com.mscatalog.models.Produto;
import br.com.mscatalog.repository.ProdutoRepository;
import br.com.mscatalog.service.ProdutoService;
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
public class ProdutoServiceImp implements ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;


    @Override
    public ResponseEntity<ProdutoDto> salvarProduto(@RequestBody @Valid ProdutoForm produtoForm) {
        Produto produto = produtoForm.converter();
        if (produto != null) {
            produtoRepository.save(produto);
            return new ResponseEntity<ProdutoDto>(new ProdutoDto(produto), HttpStatus.CREATED);
        }
        return new ResponseEntity<ProdutoDto>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public List<ProdutoDto> ListarProdutos() {
        List<Produto> produtos = produtoRepository.findAll();
        try {
            if (produtos != null) {
                return ProdutoDto.converter(produtos);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public ResponseEntity<ProdutoDto> buscarPorId(@PathVariable UUID id) {
        Optional<Produto> optional = produtoRepository.findById(id);
        if (optional.isPresent()) {
            return new ResponseEntity<ProdutoDto>(new ProdutoDto(optional.get()), HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<ProdutoDto> deletarPorId(@PathVariable UUID id) {
        Optional<Produto> optional = produtoRepository.findById(id);
        if (optional.isPresent()) {
            produtoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<ProdutoDto> atualizarPorId(@PathVariable UUID id,@RequestBody @Valid ProdutoForm produtoForm) {
        Optional<Produto> optional = produtoRepository.findById(id);
        if (optional.isPresent()) {
            Produto produto = produtoForm.converter();
            produtoRepository.save(produto);
            return ResponseEntity.ok(new ProdutoDto(produto));
        }
        return ResponseEntity.notFound().build();
    }




}
