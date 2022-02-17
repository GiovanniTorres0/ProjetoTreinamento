package com.example.controller;


import com.example.dto.UsuarioDto;
import com.example.form.LoginForm;
import com.example.form.UsuarioForm;
import com.example.repository.UsuarioRepository;
import com.example.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
public class UsuarioController {


    @Autowired
    private UsuarioService usuarioService;


    @GetMapping("/hello-world")
    public String HelloWorld() {
        return "Hello World";
    }


    @PostMapping("/v1/login")
    public ResponseEntity<UsuarioDto> login(@RequestBody @Valid LoginForm loginForm) throws Exception {
    return new ResponseEntity<UsuarioDto>(usuarioService.login(loginForm).getStatusCode());
    }


    @PostMapping("/v1/users")
    public ResponseEntity<UsuarioDto> cadastro(@RequestBody @Valid UsuarioForm usuarioForm) throws Exception {
        return new ResponseEntity<UsuarioDto>(usuarioService.save(usuarioForm).getStatusCode());
    }

    @GetMapping("/v1/users/{id}")
    public ResponseEntity<UsuarioDto> findById(@PathVariable UUID id) {
        return new ResponseEntity<UsuarioDto>(usuarioService.findById(id).getStatusCode());
    }


}








