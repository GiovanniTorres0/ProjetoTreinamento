package com.mscatalog.catalog.services.serviceimp;

import com.mscatalog.catalog.dto.VariacaoDto;
import com.mscatalog.catalog.entity.Produto;
import com.mscatalog.catalog.entity.Variacao;
import com.mscatalog.catalog.form.VariacaoForm;
import com.mscatalog.catalog.repository.ProdutoRepository;
import com.mscatalog.catalog.repository.VariacaoRepository;
import com.mscatalog.catalog.services.sequence.SequenceGeneratorService;
import com.mscatalog.catalog.services.service.VariacaoService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class VariacaoServiceImp implements VariacaoService {

    @Autowired
    VariacaoRepository variacaoRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    SequenceGeneratorService sequenceGeneratorService;


    @Transactional
    @Override
    public ResponseEntity<VariacaoDto> cadastraVariacao(@RequestBody @Valid List<VariacaoForm> listForm) {
        VariacaoForm variacaoForm = new VariacaoForm();
        List<Variacao> variacaos = variacaoForm.converterLista(listForm);
        for (int i = 0; i < listForm.size(); i++) {
            Optional<Produto> optional = produtoRepository.findById(listForm.get(i).getProduct_id());
            if (optional.isEmpty()) {
                return ResponseEntity.notFound().build();
            } else if (optional.get().getId() == variacaos.get(i).getProduct_id()) {
                Variacao var = new Variacao();
                var.setId(sequenceGeneratorService.getSequenceNumber(SEQUENCE_NAME));
                var.setSize(variacaos.get(i).getSize());
                var.setPrice(variacaos.get(i).getPrice());
                var.setQuantity(variacaos.get(i).getQuantity());
                var.setColor(variacaos.get(i).getColor());
                var.setProduct_id(variacaos.get(i).getProduct_id());
                variacaoRepository.save(var);
                List<Variacao> variacaoList = new LinkedList<>();
                variacaoList.add(var);
                Produto produto = optional.get();
                produto.setVariacoes(variacaoList);
                produtoRepository.save(produto);
            } else {
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.ok().build();
    }


    @Transactional
    @Override
    public ResponseEntity<VariacaoDto> atualizaVariacao(@RequestBody List<Variacao> variacaos, @PathVariable Integer id) {
        Variacao variacao[] = new Variacao[variacaos.size()];
        for (int i = 0; i < variacaos.size(); i++) {
            Optional<Variacao> optional = variacaoRepository.findById(id);
            if (optional.isPresent()) {
                Optional<Produto> produtoOptional = produtoRepository.findById(optional.get().getProduct_id());
                if (produtoOptional.isPresent()) {
                    variacao[i] = new Variacao(optional.get().getId(), optional.get().getColor(), optional.get().getSize(), optional.get().getPrice(), optional.get().getQuantity(), optional.get().getProduct_id());
                    Produto produto = produtoOptional.get();
                    produto.setVariacoes(List.of(variacao));
                    produtoRepository.save(produto);
                    variacaoRepository.save(variacao[i]);
                    return ResponseEntity.ok().build();
                } else {
                    return ResponseEntity.notFound().build();
                }
            }
        }
        return ResponseEntity.notFound().build();
    }
    @Override
    public ResponseEntity<VariacaoDto> deletarVariacao(@PathVariable Integer id) {
        Optional<Variacao> optional = variacaoRepository.findById(id);
        if (optional.isPresent()) {
            variacaoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
