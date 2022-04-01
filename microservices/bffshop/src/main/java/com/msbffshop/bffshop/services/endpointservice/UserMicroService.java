package com.msbffshop.bffshop.services.endpointservice;

import com.mscustomer.customer.dto.TokenDto;
import com.mscustomer.customer.dto.UsuarioDto;
import com.mscustomer.customer.form.AtualizarUsuarioForm;
import com.mscustomer.customer.form.LoginForm;
import com.mscustomer.customer.form.UsuarioForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
@FeignClient(name = "customer", url = "http://localhost:8080/v1")
public interface UserMicroService {


    @RequestMapping(method = RequestMethod.POST, value = "/users")
    ResponseEntity<UsuarioDto> salvaUsuario(@RequestBody @Valid UsuarioForm usuarioForm);

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    ResponseEntity<TokenDto> autenticar(@RequestBody @Valid LoginForm form);

    @RequestMapping(method = RequestMethod.GET, value = "/users/{id}")
    ResponseEntity<UsuarioDto> buscaUsuarioPorId(@PathVariable(value = "id") Integer id);

    @RequestMapping(method = RequestMethod.PUT, value = "/users/{id}")
    ResponseEntity<UsuarioDto> atualizarUsuarioPorId(@RequestBody @Valid AtualizarUsuarioForm atualizarUsuarioForm, @PathVariable(value = "id") Integer id);

}
