package com.msbffshop.bffshop.config.security;

import com.msbffshop.bffshop.services.securityservice.GeraTokenService;
import com.mscustomer.customer.entity.Usuario;
import com.mscustomer.customer.form.LoginForm;
import com.mscustomer.customer.form.UsuarioForm;
import com.mscustomer.customer.services.service.MicroCostumerService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {


    private GeraTokenService tokenService;

    private MicroCostumerService microCostumerService;


    public AutenticacaoViaTokenFilter(GeraTokenService tokenService, MicroCostumerService microCostumerService) {
        this.tokenService = tokenService;
        this.microCostumerService = microCostumerService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = recuperarToken(request);

        boolean valido = false;

        valido = tokenService.isTokenValido(token);


        if (valido) {
            autenticarCliente(token);
        }

        filterChain.doFilter(request, response);
    }

    private void autenticarCliente(String token) {

        Long idUsuario = tokenService.getIdUsuario(token);

        Optional<Usuario> usuario = microCostumerService.findById(idUsuario.intValue());
        LoginForm loginForm = new LoginForm(usuario.get().getEmail(),usuario.get().getPassword());
        UsuarioForm usuarioForm = new UsuarioForm();
        if (usuario.isPresent()) {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(loginForm, null, usuarioForm.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
            System.out.println("Error de Autenticação");
        }

    }

    private String recuperarToken(HttpServletRequest request) {
        String token = request.getHeader("Autorizado");
        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;

        }
        return token.substring(7, token.length());
    }

}





