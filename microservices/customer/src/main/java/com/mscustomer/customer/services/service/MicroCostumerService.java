package com.mscustomer.customer.services.service;

import com.mscustomer.customer.entity.Usuario;

import java.util.Optional;

public interface MicroCostumerService {
    Optional<Usuario> findById(int intValue);

    Optional<Usuario> findByEmail(String email);
}
