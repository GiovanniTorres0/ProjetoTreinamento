package com.mscustomer.customer.form;


import com.mscustomer.customer.config.validacao.ValidCPF;
import com.mscustomer.customer.entity.Perfil;
import com.mscustomer.customer.entity.Usuario;
import com.mscustomer.customer.enums.Sex;
import com.mscustomer.customer.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioForm implements UserDetails {

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
    @OneToOne
    private List<Perfil> perfis = new ArrayList<>();

    public Usuario converter(UsuarioForm form, UsuarioRepository usuarioRepository) throws Exception {
        Usuario usuario = new Usuario();
        ValidCPF valida = new ValidCPF();
        LocalDate hoje = LocalDate.parse(form.getBirthdate());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String hojeFormatado = hoje.format(formatter);
        usuario.setFirstName(form.getFirstName());
        usuario.setLastName(form.getLastName());
        usuario.setSex(form.getSex());
        usuario.setPassword(form.getPassword());
        usuario.setBirthdate(hojeFormatado);
        Optional<Usuario> optional = usuarioRepository.findByEmail(form.getEmail());
        if (optional.isEmpty() && valida.isCPF(form.getCpf()) == true) {
            usuario.setEmail(form.getEmail());
            usuario.setCpf(form.getCpf());

            return usuario;
        } else {
            throw new Exception("Email Duplicado ou CPF errado");
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.perfis;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public String getPassword(){
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}






