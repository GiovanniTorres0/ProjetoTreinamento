package com.example.form;

import com.example.models.Usuario;
import com.example.repository.UsuarioRepository;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LoginForm {

    @NotBlank
    String email;
    @NotBlank
    @Size(min = 8)
    String password;


    public LoginForm() {

    }

    public LoginForm(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Usuario login(UsuarioForm form, UsuarioRepository usuarioRepository) {
        Usuario usuario = new Usuario();
        usuario.setEmail(form.getEmail());
        usuario.setPassword(form.getPassword());
        return usuario;
    }


    public Usuario converter() {
        return new Usuario(email, password);
    }
}
