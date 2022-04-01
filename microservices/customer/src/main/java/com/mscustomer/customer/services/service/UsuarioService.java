package com.mscustomer.customer.services.service;

import com.mscustomer.customer.dto.UsuarioDto;
import com.mscustomer.customer.form.AtualizarUsuarioForm;
import com.mscustomer.customer.form.UsuarioForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.text.ParseException;

public  interface UsuarioService {


    @Transactional
    ResponseEntity<UsuarioDto> save(@Valid @RequestBody UsuarioForm form) throws Exception;

    ResponseEntity<UsuarioDto> findById(@PathVariable Integer id);

    @Transactional
    ResponseEntity<UsuarioDto> update(@RequestBody @Valid AtualizarUsuarioForm atualizarUsuarioForm, @PathVariable Integer id) throws ParseException;

    UsuarioDto retorna(Integer id);


}
