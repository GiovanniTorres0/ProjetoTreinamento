package com.mscustomer.customer.controller;

import com.mscustomer.customer.dto.TokenDto;
import com.mscustomer.customer.form.LoginForm;
import com.mscustomer.customer.services.securityservice.GeraTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/login")
public class LoginController {


    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private GeraTokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid LoginForm form){
        UsernamePasswordAuthenticationToken dadosLogin = form.converter();
        try {
            Authentication authentication = authManager.authenticate(dadosLogin);
            String token = tokenService.gerarToken(authentication);
            System.out.println("GENRACION DE TOKEN: "+token);
            return ResponseEntity.ok(new TokenDto(token, "Bearer"));
        }catch ( AuthenticationException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }


    }
}







