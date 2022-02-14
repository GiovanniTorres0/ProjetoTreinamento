package br.com.mscustomer.controller;
import br.com.mscustomer.models.User;
import br.com.mscustomer.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {


   @Autowired
    private UserService userService;


   @GetMapping(value = "/v1/users/:id")
    public List<User> getAll() {
       List<User> usuarios = userService.getAll();
       return usuarios;
   }


}
