package com.mscatalog.catalog.services.service;

import com.mscatalog.catalog.dto.VariacaoDto;
import com.mscatalog.catalog.entity.Variacao;
import com.mscatalog.catalog.form.VariacaoForm;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@Service
public interface VariacaoService {


    @Transactional
    ResponseEntity<VariacaoDto> cadastraVariacao(@RequestBody @Valid List<VariacaoForm> listForm) throws Exception;

    @Transactional
    ResponseEntity<VariacaoDto> atualizaVariacao(@RequestBody List<Variacao> variacaos, @PathVariable Integer id);

    @Transactional

    ResponseEntity<VariacaoDto> deletarVariacao(@PathVariable Integer id);
}
