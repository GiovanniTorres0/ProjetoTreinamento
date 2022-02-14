package br.com.mscustomer.services;

import br.com.mscustomer.models.User;

import java.io.Serializable;
import java.util.List;


public interface UserService<User, Long extends Serializable> {


    User save(User entity);

    void delete(Long id);

    User get(Long id);

    List<User> getAll();
}
