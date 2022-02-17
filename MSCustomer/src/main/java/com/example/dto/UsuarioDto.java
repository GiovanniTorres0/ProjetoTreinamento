package com.example.dto;

import com.example.models.Usuario;
import com.example.enums.Sex;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;

public class UsuarioDto {

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @Enumerated(value = EnumType.STRING)
    private Sex sex;
    @NotBlank
    private String cpf;
    @NotBlank
    private String birthdate;
    @NotBlank
    private String email;


    public UsuarioDto(Usuario usuario) {
        this.firstName = usuario.getFirstName();
        this.lastName = usuario.getLastName();
        this.sex = usuario.getSex();
        this.cpf = usuario.getCpf();
        this.birthdate = usuario.getBirthdate();
        this.email = usuario.getEmail();
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static List<UsuarioDto> converter(List<Usuario> usuarios) {
        return usuarios.stream().map(UsuarioDto::new).collect(Collectors.toList());
    }


}




