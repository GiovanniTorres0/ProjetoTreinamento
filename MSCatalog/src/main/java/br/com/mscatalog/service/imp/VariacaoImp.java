package br.com.mscatalog.service.imp;

import br.com.mscatalog.dto.VariacaoDto;
import br.com.mscatalog.form.VariacaoForm;
import br.com.mscatalog.models.Variacao;
import br.com.mscatalog.repository.VariacaoRepository;
import br.com.mscatalog.service.VariacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@Service
public class VariacaoImp implements VariacaoService {

    @Autowired
    VariacaoRepository variacaoRepository;


    @Override
    public ResponseEntity<VariacaoDto> cadastrarVariacao(@RequestBody VariacaoForm variacaoForm){
        Variacao variacao = variacaoForm.converter();
        if(variacao != null) {
            variacao = variacaoRepository.save(variacao);
            return new ResponseEntity<VariacaoDto>(new VariacaoDto(variacao),HttpStatus.CREATED);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


    @Override
    public ResponseEntity<VariacaoDto> atualizarVariacao(@PathVariable UUID id, @Valid @RequestBody VariacaoForm variacaoForm){
        Optional<Variacao> optional = variacaoRepository.findById(id);
        if(optional.isPresent()){
            Variacao variacao = variacaoForm.converter();
            variacaoRepository.save(variacao);
            return new ResponseEntity<VariacaoDto>(new VariacaoDto(variacao), HttpStatus.ACCEPTED);
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

    @Override
    public ResponseEntity<VariacaoDto> deletarVariacao(@PathVariable UUID id){
        Optional<Variacao> optional = variacaoRepository.findById(id);
        if(optional.isPresent()){
            variacaoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
