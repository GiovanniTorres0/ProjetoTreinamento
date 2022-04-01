package com.msbffshop.bffshop.controller;

import com.msbffshop.bffshop.services.endpointservice.UserMicroService;
import com.mscustomer.customer.dto.TokenDto;
import com.mscustomer.customer.dto.UsuarioDto;
import com.mscustomer.customer.form.AtualizarUsuarioForm;
import com.mscustomer.customer.form.LoginForm;
import com.mscustomer.customer.form.UsuarioForm;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1")
public class BffUserController {


    @Autowired
    UserMicroService usermicroService;

    @ApiOperation(value = "Cadastra um Usuário", notes = "Envia os dados para cadastro de usuario", response = BffUserController.class)
    @ApiResponses({@ApiResponse(code = 201, message = "Created"), @ApiResponse(code = 400, message = "Bad Request")})
    @PostMapping("/users")
    public ResponseEntity<UsuarioDto> salvaUsuario(@RequestBody @Valid UsuarioForm usuarioForm){
        return usermicroService.salvaUsuario(usuarioForm);
    }
    @ApiOperation(value = "Dados para Login", notes = "Envia os dados para validação de login", response = BffUserController.class)
    @ApiResponses({@ApiResponse(code = 201, message = "Created"), @ApiResponse(code = 400, message = "Bad Request")})
    @PostMapping("/login")
    public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid LoginForm form){
        return  usermicroService.autenticar(form);
    }
    @ApiOperation(value = "Busca usuario por Id", notes = "Busca usuario pelo Integer", response = BffUserController.class)
    @ApiResponses({@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 404, message = "Not Found")})
    @GetMapping("/users/{id}")
    public ResponseEntity<UsuarioDto> buscaUsuarioPorId(@PathVariable(value = "id") Integer id){
        return usermicroService.buscaUsuarioPorId(id);
    }

    @ApiOperation(value = "Atualiza o usuario", notes = "Busca usuario pelo UUID e atualiza", response = BffUserController.class)
    @ApiResponses({@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 404, message = "Not Found")})
    @PutMapping("/users/{id}")
    public ResponseEntity<UsuarioDto> atualizarUsuarioPorId(@RequestBody @Valid AtualizarUsuarioForm atualizarUsuarioForm, @PathVariable(value = "id") Integer id){
        return usermicroService.atualizarUsuarioPorId(atualizarUsuarioForm, id);
    }


}





