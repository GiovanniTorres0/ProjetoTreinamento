package com.msbffshop.bffshop.controller;

import com.msbffshop.bffshop.services.endpointservice.HistoryMicroService;
import com.mshistory.history.dto.HistoryDto;
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
public class BffHistoryController {


    @Autowired
    HistoryMicroService historyMicroService;

    @ApiOperation(value = "Busca historico por id", notes = "Busca todas as informações de um usuario pelo id", response = BffHistoryController.class)
    @ApiResponses({@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 404, message = "Not Found")})
    @GetMapping("/historic/user/{id}")
    public ResponseEntity<HistoryDto> buscaHistorico(@PathVariable(value = "id") Integer id){
        return historyMicroService.buscaHistorico(id);
    }


}
