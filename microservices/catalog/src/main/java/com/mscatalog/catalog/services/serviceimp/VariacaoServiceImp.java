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
    public ResponseEntity<VariacaoDto> cadastraVariacao(@RequestBody @Valid List<VariacaoForm> listForm) throws Exception {
        VariacaoForm variacaoForm = new VariacaoForm();

        for(int i =0; i< listForm.size(); i++){
            Optional<Produto> optional = produtoRepository.findById(listForm.get(i).getProduct_id());
           if(optional.isEmpty()){
                throw new Exception("PRODUTO PARA VINCULAR INEXISTENTE");
            }
        }
        List<Variacao> variacoes = variacaoForm.converter(listForm);
        for(Variacao var : variacoes){
            if(var.getPrice() == null || var.getColor() == null || var.getProduct_id() == null || var.getSize() == null) {
                throw new Exception("PREÇO OU COR OU PRODUTO_ID ESTÁ NULO");
            }
        }
        Produto produto = variacaoForm.retorna(listForm, produtoRepository);
        if (variacoes != null && produto != null) {
            for (int i = 0; i < variacoes.size(); i++) {
                Variacao variacao = new Variacao();
                variacao.setId(sequenceGeneratorService.getSequenceNumber(SEQUENCE_NAME));
                variacoes.get(i).setId(variacao.getId());
            }
            for (int i = 0; i < variacoes.size(); i++) {
                variacaoRepository.save(variacoes.get(i));
            }

            produto.setVariacoes(variacoes);
            produtoRepository.save(produto);
            return ResponseEntity.ok().build();
        }
        return  ResponseEntity.notFound().build();
        }



    @Transactional
    @Override
    public ResponseEntity<VariacaoDto> atualizaVariacao(@RequestBody @Valid List<VariacaoForm> listForm, @PathVariable Integer id) {
        Optional<Variacao> optional = variacaoRepository.findById(id);
        if (optional.isPresent()) {
            VariacaoForm variacaoForm = new VariacaoForm();
            List<Variacao> variacoes = variacaoForm.converter(listForm);
            Produto produto = variacaoForm.retorna(listForm, produtoRepository);
            if (variacoes != null && produto != null) {
                for (int i = 0; i < variacoes.size(); i++) {
                    Variacao variacao = new Variacao();
                    variacao.setId(sequenceGeneratorService.getSequenceNumber(SEQUENCE_NAME));
                    variacoes.get(i).setId(optional.get().getId());
                }
                for (int i = 0; i < variacoes.size(); i++) {
                    variacaoRepository.save(variacoes.get(i));
                }
                produto.setVariacoes(variacoes);
                produtoRepository.save(produto);
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.badRequest().build();
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
