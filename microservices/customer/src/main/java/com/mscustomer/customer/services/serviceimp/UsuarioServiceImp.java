package com.mscustomer.customer.services.serviceimp;

import com.mscustomer.customer.dto.UsuarioDto;
import com.mscustomer.customer.entity.Usuario;
import com.mscustomer.customer.form.AtualizarUsuarioForm;
import com.mscustomer.customer.form.LoginForm;
import com.mscustomer.customer.form.UsuarioForm;
import com.mscustomer.customer.repository.PerfilRepository;
import com.mscustomer.customer.repository.UsuarioRepository;
import com.mscustomer.customer.services.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.Optional;

@Service
public class UsuarioServiceImp implements UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;



    @Autowired
    PerfilRepository perfilRepository;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();



    @Transactional
    @Override
    public ResponseEntity<UsuarioDto> save(@Valid @RequestBody UsuarioForm form) throws Exception {
        Usuario usuario = form.converter(form, usuarioRepository);
        if (usuario != null) {
             usuario.setPassword(encoder.encode(usuario.getPassword()));
            return new ResponseEntity<UsuarioDto>(new UsuarioDto(usuarioRepository.save(usuario)), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<UsuarioDto>(HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public ResponseEntity<UsuarioDto> findById(@PathVariable Integer id) {
        Optional<Usuario> optional = usuarioRepository.findById(id);
        if (optional.isPresent()) {
            Usuario usuario = optional.get();
            return new ResponseEntity<UsuarioDto>(new UsuarioDto(usuario), HttpStatus.OK);
        } else {
            return new ResponseEntity<UsuarioDto>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<UsuarioDto> login(@RequestBody @Valid LoginForm loginForm) throws Exception {
        UsernamePasswordAuthenticationToken dadosLogin = loginForm.converter();
        throw new Exception("Infelizmente não consegui descobrir como envio um metodo de um microserviço para o outro");
    }

    @Transactional
    @Override
    public ResponseEntity<UsuarioDto> update(@RequestBody @Valid AtualizarUsuarioForm atualizarUsuarioForm, @PathVariable Integer id) throws ParseException {
        Optional<Usuario> optional = usuarioRepository.findById(id);
        if (optional.isPresent()) {
            Usuario usuario = atualizarUsuarioForm.atualizar(id,usuarioRepository);
            usuario.setPassword(encoder.encode(usuario.getPassword()));
            return new ResponseEntity<UsuarioDto>(new UsuarioDto(usuario), HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();


    }

    @Override
    public UsuarioDto retorna(Integer id){
        Optional<Usuario> optional = usuarioRepository.findById(id);
        if(optional.isPresent()){
            return new UsuarioDto(optional.get());
        }
       throw new RuntimeException();
    }



}
