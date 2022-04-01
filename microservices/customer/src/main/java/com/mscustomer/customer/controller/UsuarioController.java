package com.mscustomer.customer.controller;


import com.mscustomer.customer.dto.UsuarioDto;
import com.mscustomer.customer.form.AtualizarUsuarioForm;
import com.mscustomer.customer.form.UsuarioForm;
import com.mscustomer.customer.services.service.UsuarioService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;

@RestController
public class UsuarioController {


    @Autowired
    private UsuarioService usuarioService;


    @ApiOperation(value = "Cadastra um Usuário", notes = "Envia os dados para cadastro de usuario", response = UsuarioController.class)
    @ApiResponses({@ApiResponse(code = 201, message = "Created"), @ApiResponse(code = 400, message = "Bad Request")})
    @PostMapping(value = "/v1/users")
    public ResponseEntity<UsuarioDto> cadastro(@RequestBody @Valid UsuarioForm usuarioForm) throws Exception {
        return usuarioService.save(usuarioForm);
    }

    @ApiOperation(value = "Busca usuario por Id", notes = "Busca usuario pelo Integer", response = UsuarioController.class)
    @ApiResponses({@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 404, message = "Not Found")})
    @GetMapping(value = "/v1/users/{id}")
    public ResponseEntity<UsuarioDto> findById(@PathVariable Integer id) {
        return usuarioService.findById(id);
    }

    @ApiOperation(value = "Atualiza o usuario", notes = "Busca usuario pelo UUID e atualiza", response = UsuarioController.class)
    @ApiResponses({@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 404, message = "Not Found")})
    @PutMapping(value = "/v1/users/{id}")
    public ResponseEntity<UsuarioDto> updateById(@RequestBody @Valid AtualizarUsuarioForm atualizarUsuarioForm, @PathVariable Integer id) throws ParseException {
        return usuarioService.update(atualizarUsuarioForm, id);
    }


}








