package com.example.mscustomer.models;

import com.example.mscustomer.enums.Genero;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TB_Usuario")
public class Usuario implements Serializable {


    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private Genero genero;
    private String cpf;
    private String birthdate;
    private String email;
    private String password;
    private Boolean active;

    public Usuario() {

    }

    public Usuario(String firstName, String lastName, Genero genero, String cpf, String birthdate, String email, String password, Boolean active) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.genero = genero;
        this.cpf = cpf;
        this.birthdate = birthdate;
        this.email = email;
        this.password = password;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Genero getSex() {
        return genero;
    }

    public void setSex(Genero sex) {
        this.genero = sex;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}





