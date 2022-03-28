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
    public ResponseEntity<ProdutoDto> registraProduto(@RequestBody @Valid ProdutoForm produtoForm) {
        Produto produto = produtoForm.converter(produtoForm, categoriaRepository);
        if (produto != null && produto.getCategoria().getActive()) {
            produto.setId(sequenceGeneratorService.getSequenceNumber(SEQUENCE_NAME));
            produtoRepository.save(produto);
            return new ResponseEntity<>(new ProdutoDto(produto), HttpStatus.CREATED);
        } else {
            System.out.println(" -- TALVEZ A CATEGORIA PRECISE SER CADASTRADA ANTES -- ");
            return ResponseEntity.notFound().build();
        }
    }

        @Override
        public List<ProdutosVariadosDto> buscaTodosProdutos () {
            List<Produto> produtos = produtoRepository.findAll();
            return ProdutosVariadosDto.converterProdutos(produtos);
        }

        @Override
        public ResponseEntity<ProdutoDto> buscaPorId (@PathVariable Integer id){
            Optional<Produto> optional = produtoRepository.findById(id);
            return optional.map(produto -> new ResponseEntity<>(new ProdutoDto(produto), HttpStatus.OK)).orElseGet(() -> ResponseEntity.notFound().build());
        }

    @Transactional
    @Override
    public ResponseEntity<ProdutoDto> atualizarPorId(@PathVariable Integer id, @RequestBody @Valid ProdutoForm produtoForm) {
        Optional<Produto> optional = produtoRepository.findById(id);
        if (optional.isPresent()) {
            Produto produto = produtoForm.converter(produtoForm, categoriaRepository);
            produto.setId(id);
            return new ResponseEntity<>(new ProdutoDto(produtoRepository.save(produto)), HttpStatus.ACCEPTED);
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
        public List<ProdutosVariadosDto> retorna () throws Exception {
           try {
               List<Produto> produtos = produtoRepository.findProdutoByActiveIsTrue();
               List<ProdutosVariadosDto> lista = new LinkedList<>();
               return ProdutosVariadosDto.converterProdutos(produtos);
           } catch(Exception e) {
               throw new Exception("NÃO LOCALIZOU PRODUTOS ATIVOS");
           }
        }


        @Override
        public double valor () throws Exception {
            double valor = 0;
            for (int i = 0; i < retorna().size(); i++) {
                valor += retorna().get(i).getVariacoes().get(i).getPrice();
            }
            return valor;
        }


    }
