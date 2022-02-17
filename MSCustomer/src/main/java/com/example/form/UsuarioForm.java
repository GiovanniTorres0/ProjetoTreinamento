package com.example.form;


import com.example.config.ValidCPF;
import com.example.enums.Sex;
import com.example.models.Usuario;
import com.example.repository.UsuarioRepository;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Optional;


public class UsuarioForm {

    @NotBlank
    @Size(min = 3, message = "firstName too short")
    private String firstName;
    @NotBlank
    @Size(min = 3, message = "lastName too short")
    private String lastName;
    @Enumerated(value = EnumType.STRING)
    private Sex sex;
    @NotBlank
    @Email(message = "Your Email is not valid")
    private String email;
    @NotBlank
    @Size(min = 8, max = 10)
    private String cpf;
    @NotBlank
    @Size(min = 8, message = "password too short")
    private String password;
    @NotBlank
    private String birthdate;

    public UsuarioForm() {

    }

    public UsuarioForm(String firstName, String lastName, Sex sex, String email, String cpf, String password, String birthdate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.email = email;
        this.cpf = cpf;
        this.password = password;
        this.birthdate = birthdate;
    }


    public Usuario converter(UsuarioForm form, UsuarioRepository usuarioRepository) {
        Usuario usuario = new Usuario();
        usuario.setFirstName(form.getFirstName());
        usuario.setLastName(form.getLastName());
        usuario.setSex(form.getSex());
        usuario.setPassword(form.getPassword());
        usuario.setBirthdate(form.getBirthdate());
        try {
            if (form.isNotPresentEmail(form.getEmail(), usuarioRepository) == true && ValidCPF.isCPF(cpf) == true) {
                usuario.setEmail(form.getEmail());
                usuario.setCpf(form.getCpf());
                return usuario;
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return usuario;
    }



    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }


    private boolean isNotPresentEmail(String email, UsuarioRepository usuarioRepository) {
        Optional<Usuario> ValidarEmail = usuarioRepository.findByEmail(email);
        if (ValidarEmail.isPresent()) {
            return false;
        }
        return true;
    }


}






