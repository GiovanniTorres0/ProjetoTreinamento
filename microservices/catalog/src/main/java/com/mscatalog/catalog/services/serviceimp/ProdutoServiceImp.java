package com.mscatalog.catalog.services.serviceimp;

import com.mscatalog.catalog.dto.ProdutoDto;
import com.mscatalog.catalog.dto.ProdutosVariadosDto;
import com.mscatalog.catalog.entity.Produto;
import com.mscatalog.catalog.form.ProdutoForm;
import com.mscatalog.catalog.repository.CategoriaRepository;
import com.mscatalog.catalog.repository.ProdutoRepository;
import com.mscatalog.catalog.services.sequence.SequenceGeneratorService;
import com.mscatalog.catalog.services.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.mscatalog.catalog.entity.Produto.SEQUENCE_NAME;


@Service
public class ProdutoServiceImp implements ProdutoService {


    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    SequenceGeneratorService sequenceGeneratorService;


    @Transactional
    @Override
    public ResponseEntity<ProdutoDto> registraProduto(@RequestBody @Valid ProdutoForm produtoForm) throws Exception {
        Produto produto = produtoForm.converter(produtoForm, categoriaRepository);
        if (produto != null) {
            if (produto.getCategoria().getActive()) {
                produto.setId(sequenceGeneratorService.getSequenceNumber(SEQUENCE_NAME));
                return new ResponseEntity<ProdutoDto>(new ProdutoDto(produtoRepository.save(produto)), HttpStatus.CREATED);
            }
        }
        throw new Exception("---É preciso que a categoria seja cadastrada antes---");
    }

    @Override
    public List<ProdutosVariadosDto> buscaTodosProdutos() {
        List<Produto> produtos = produtoRepository.findAll();
        if (produtos != null) {
            List<ProdutosVariadosDto> converter = ProdutosVariadosDto.converterProdutos(produtos);
            return converter;
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public ResponseEntity<ProdutoDto> buscaPorId(@PathVariable Integer id) {
        Optional<Produto> optional = produtoRepository.findById(id);
        if (optional.isPresent()) {
            return new ResponseEntity<ProdutoDto>(new ProdutoDto(optional.get()), HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional
    @Override
    public ResponseEntity<ProdutoDto> atualizarPorId(@PathVariable Integer id, @RequestBody @Valid ProdutoForm produtoForm) {
        Optional<Produto> optional = produtoRepository.findById(id);
        if (optional.isPresent()) {
            Produto produto = produtoForm.converter(produtoForm, categoriaRepository);
            produto.setId(id);
            return new ResponseEntity<ProdutoDto>(new ProdutoDto(produtoRepository.save(produto)), HttpStatus.ACCEPTED);
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional
    @Override
    public ResponseEntity<ProdutoDto> deletarPorId(@PathVariable Integer id) {
        Optional<Produto> optional = produtoRepository.findById(id);
        if (optional.isPresent()) {
            produtoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public List<ProdutosVariadosDto> retorna() {
        List<Produto> produtos = produtoRepository.findProdutoByActiveIsTrue();
        List<ProdutosVariadosDto> lista = new LinkedList<>();
        return lista = ProdutosVariadosDto.converterProdutos(produtos);

    }



    @Override
    public double valor() {
    double valor = 0;
    for (int i = 0; i < retorna().size(); i++) {
            valor += retorna().get(i).getVariacoes().get(i).getPrice();
        }
    return valor;
    }


}
