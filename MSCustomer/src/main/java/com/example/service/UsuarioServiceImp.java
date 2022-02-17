package com.example.service;

import com.example.dto.UsuarioDto;
import com.example.form.LoginForm;
import com.example.form.UsuarioForm;
import com.example.models.Usuario;
import com.example.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioServiceImp implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Transactional
    @Override
    public ResponseEntity<UsuarioDto> save(@Valid @RequestBody UsuarioForm form) {
        Usuario usuario = form.converter(form, usuarioRepository);
        if (usuario != null) {
            usuario = usuarioRepository.save(usuario);
            return new ResponseEntity<UsuarioDto>(new UsuarioDto(usuario), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<UsuarioDto>(HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public ResponseEntity<UsuarioDto> findById (@PathVariable UUID id) {
        Optional<Usuario> optional = usuarioRepository.findById(id);
        if(optional.isPresent()){
            Usuario usuario = optional.get();
            return new ResponseEntity<UsuarioDto>(new UsuarioDto(usuario), HttpStatus.OK);
        } else {
            return new ResponseEntity<UsuarioDto>(HttpStatus.BAD_REQUEST);
        }
    }




    @Override
    public ResponseEntity<UsuarioDto> login(@RequestBody @RequestParam LoginForm loginForm) {
        Usuario usuario = loginForm.converter();
        if (usuario != null) {
            usuario = usuarioRepository.save(usuario);
            return new ResponseEntity<UsuarioDto>(new UsuarioDto(usuario), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<UsuarioDto>(HttpStatus.BAD_REQUEST);
        }

    }
}






