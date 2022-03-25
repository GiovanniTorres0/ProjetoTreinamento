package com.msbffshop.bffshop.services.securityservice;

import com.mscustomer.customer.entity.Usuario;
import com.mscustomer.customer.form.UsuarioForm;
import com.mscustomer.customer.services.service.MicroCostumerService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {

    private MicroCostumerService microCostumerService;

    @Override
    public UserDetails  loadUserByUsername(String email) {
        Optional<Usuario> usuario = microCostumerService.findByEmail(email);
        UsuarioForm usuarioForm = new UsuarioForm();
        usuarioForm.setEmail(usuario.get().getEmail());
        usuarioForm.setPassword(usuario.get().getPassword());
        if (usuario.isPresent()) {
            return usuarioForm;
        }
        throw new UsernameNotFoundException("Dados ivalidos");
    }



}
