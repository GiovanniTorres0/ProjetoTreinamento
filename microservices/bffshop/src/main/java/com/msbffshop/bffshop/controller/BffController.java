package com.msbffshop.bffshop.controller;

import com.mscustomer.customer.services.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/v1")
public class BffController {

    UsuarioService usuarioService;

    @Autowired
    RestTemplate restTemplate;

//    @PostMapping("/users")
//    public void cadastraUsuario(@RequestBody @Valid UsuarioForm usuarioForm) throws ParseException {
//
//    }
//


}





