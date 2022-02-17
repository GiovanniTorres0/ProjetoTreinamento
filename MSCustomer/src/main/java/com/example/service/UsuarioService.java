package com.example.service;

import com.example.dto.UsuarioDto;
import com.example.form.LoginForm;
import com.example.form.UsuarioForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.UUID;


public interface UsuarioService {


    @Transactional
    ResponseEntity<UsuarioDto> save(@Valid @RequestBody UsuarioForm form);

    ResponseEntity<UsuarioDto> findById(@PathVariable UUID id);

    ResponseEntity<UsuarioDto> login(@RequestBody @RequestParam LoginForm loginForm);
}
