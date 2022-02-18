package br.com.mscatalog.controller;

import br.com.mscatalog.dto.VariacaoDto;
import br.com.mscatalog.form.VariacaoForm;
import br.com.mscatalog.service.VariacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(value = "/v1")
public class VariacaoController {

    @Autowired
    VariacaoService variacaoService;


    @PostMapping(value = "/variations")
    public ResponseEntity<VariacaoDto> cadastrarVariacao(@RequestBody @Valid VariacaoForm variacaoForm) {
        return variacaoService.cadastrarVariacao(variacaoForm);
    }

    @PutMapping(value = "/variations/{id}")
    public ResponseEntity<VariacaoDto> atualizarVariacao(@PathVariable UUID id, @RequestBody @Valid VariacaoForm variacaoForm) {
        return variacaoService.atualizarVariacao(id, variacaoForm);
    }

    @DeleteMapping(value = "/variations/{id}")
    public ResponseEntity<VariacaoDto> deletarVariacao(@PathVariable UUID id) {
        return variacaoService.deletarVariacao(id);
    }
}
