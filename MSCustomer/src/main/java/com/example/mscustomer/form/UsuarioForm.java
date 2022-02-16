package com.example.mscustomer.form;


import com.example.mscustomer.enums.Genero;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class UsuarioForm {

    @NotEmpty(message = "firstName Empty")
    @NotNull(message = "firstName Null")
    @Size(min = 3, message = "firstName too short")
    private String firstName;
    @NotEmpty(message = "lastName Empty")
    @NotNull(message = "lastName Null")
    @Size(min = 3, message = "lastName too short")
    private String lastName;
    @NotEmpty(message = "sex Empty")
    @NotNull(message = "sex Null")
    private Genero genero;
    @NotEmpty(message = "sex Empty")
    @NotNull(message = "sex Null")
    @Email(message = "Your Email is not valid")
    private String email;
    @NotEmpty(message = "cpf Empty")
    @NotNull(message = "cpf Null")
    @Size(min = 11, max = 11)
    private String cpf;
    @NotEmpty(message = "password Empty")
    @NotNull(message = "password Null")
    @Size(min = 8, message = "password too short")
    private String password;
    @NotEmpty(message = "birthdate Empty")
    @NotNull(message = "birthdate Null")
    private String birthdate;

    public UsuarioForm() {

    }

    public UsuarioForm(String firstName, String lastName, Genero genero, String email, String cpf, String password, String birthdate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.genero = genero;
        this.email = email;
        this.cpf = cpf;
        this.password = password;
        this.birthdate = birthdate;
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

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
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
}
