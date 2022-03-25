package com.mscatalog.catalog.controller;

import com.mscatalog.catalog.dto.VariacaoDto;
import com.mscatalog.catalog.form.VariacaoForm;
import com.mscatalog.catalog.services.service.VariacaoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1")
public class VariacaoController {

    @Autowired
    VariacaoService variacaoService;

    @ApiOperation(value = "Cadastra uma Lista de Variações", notes = "Cadastro de uma Variacao de Produto", response = VariacaoController.class)
    @ApiResponses({@ApiResponse(code = 200, message = "OK")})
    @Transactional
    @PostMapping("/variations")
    public ResponseEntity<VariacaoDto> cadastraVariacao(@RequestBody @Valid List<VariacaoForm> variacaoForm) throws Exception {
        return variacaoService.cadastraVariacao(variacaoForm);
    }

    @ApiOperation(value = "Atualiza uma Variacao", notes = "Atualiza uma Variacao de Produto", response = VariacaoController.class)
    @ApiResponses({@ApiResponse(code = 200, message = "OK")})
    @Transactional
    @PutMapping("/variations/{id}")
    public ResponseEntity<VariacaoDto> atualizarVariacao(@RequestBody @Valid List<VariacaoForm> listForm, @PathVariable Integer id) {
        return variacaoService.atualizaVariacao(listForm, id);
    }

    @ApiOperation(value = "Deleta uma Variacao", notes = "Deleta uma Variacao de Produto", response = VariacaoController.class)
    @ApiResponses({@ApiResponse(code = 200, message = "OK")})
    @DeleteMapping("/variations/{id}")
    public ResponseEntity<VariacaoDto> deletarVariacao(@PathVariable Integer id) {
        return variacaoService.deletarVariacao(id);
    }



}
