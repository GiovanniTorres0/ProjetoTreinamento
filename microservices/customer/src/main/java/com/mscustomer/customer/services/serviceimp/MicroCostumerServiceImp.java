package com.mscustomer.customer.services.serviceimp;

import com.mscustomer.customer.entity.Usuario;
import com.mscustomer.customer.repository.UsuarioRepository;
import com.mscustomer.customer.services.service.MicroCostumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MicroCostumerServiceImp implements MicroCostumerService {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public Optional<Usuario> findById(int intValue) {
        Optional<Usuario> optional =usuarioRepository.findById(intValue);
        return optional;
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        Optional<Usuario> optional = usuarioRepository.findByEmail(email);
        return  optional;
    }
}
