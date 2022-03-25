package com.mscustomer.customer.form;

import com.mscustomer.customer.config.validacao.ValidCPF;
import com.mscustomer.customer.entity.Usuario;
import com.mscustomer.customer.enums.Sex;
import com.mscustomer.customer.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AtualizarUsuarioForm {

    @NotNull
    @NotEmpty
    @Size(min = 3, message = "firstName too short")
    private String firstName;
    @NotNull
    @NotEmpty
    @Size(min = 3, message = "lastName too short")
    private String lastName;
    @Enumerated(value = EnumType.STRING)
    private Sex sex;
    @NotNull
    @NotEmpty
    @Email(message = "Your Email is not valid")
    private String email;
    @NotNull
    @NotEmpty
    @Size(min = 11, max = 14)
    private String cpf;
    @NotNull
    @NotEmpty
    @Size(min = 8, message = "password too short")
    private String password;
    private String birthdate;



    public Usuario atualizar(Integer id, UsuarioRepository usuarioRepository) throws ParseException {
        Usuario usuario = usuarioRepository.getById(id);
        ValidCPF validCPF = new ValidCPF();
        usuario.setFirstName(this.firstName);
        usuario.setLastName(this.lastName);
        usuario.setSex(this.sex);
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(this.email);
        if(usuarioOptional.isEmpty() && validCPF.isCPF(this.cpf)) {
            usuario.setEmail(this.email);
            usuario.setCpf(this.cpf);
        }
        usuario.setPassword(this.password);
        LocalDate hoje = LocalDate.parse(this.birthdate);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String hojeFormatado = hoje.format(formatter);
        usuario.setBirthdate(hojeFormatado);
        return usuario;
    }


}
