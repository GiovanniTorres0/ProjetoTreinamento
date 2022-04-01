package com.mscustomer.customer.dto;

import com.mscustomer.customer.entity.Usuario;
import com.mscustomer.customer.enums.Sex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto {

    private Integer id;
    private String firstName;
    private String lastName;
    @Enumerated(value = EnumType.STRING)
    private Sex sex;
    private String cpf;
    private String birthdate;
    private String email;


    public UsuarioDto(Usuario usuario) {
        this.id = usuario.getId();
        this.firstName = usuario.getFirstName();
        this.lastName = usuario.getLastName();
        this.sex = usuario.getSex();
        this.cpf = usuario.getCpf();
        this.birthdate = usuario.getBirthdate();
        this.email = usuario.getEmail();
    }

    public static List<UsuarioDto> converter(List<Usuario> usuarios) {
        return usuarios.stream().map(UsuarioDto::new).collect(Collectors.toList());
    }



}




