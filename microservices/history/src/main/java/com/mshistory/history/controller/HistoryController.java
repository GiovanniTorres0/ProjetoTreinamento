package com.mshistory.history.controller;

import com.mshistory.history.dto.HistoryDto;
import com.mshistory.history.services.service.HistoryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class HistoryController {


    @Autowired
    HistoryService historyService;

    @ApiOperation(value = "Busca o Historico pelo id do usuario", notes = "Busca historico pelo id", response = HistoryController.class)
    @ApiResponses({@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 204, message = "No Content")})
    @GetMapping("/historic/user/{id}")
    public ResponseEntity<HistoryDto> buscaHistorico(@PathVariable(value = "id") Integer id){
        return historyService.buscaHistoricoUsuario(id);
    }


}
