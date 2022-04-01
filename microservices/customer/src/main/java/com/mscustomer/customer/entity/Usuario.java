package com.mscustomer.customer.entity;

import com.mscustomer.customer.enums.Sex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_Usuario")
public class Usuario implements Serializable, UserDetails {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "user_id_sequence",
            sequenceName = "user_id_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_sequence")
    private Integer id;
    private String firstName;
    private String lastName;
    @Enumerated(value = EnumType.STRING)
    private Sex sex;
    @Column(unique = true)
    private String cpf;
    private String birthdate;
    @Column(unique = true)
    private String email;
    private String password;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Perfil> perfis = new ArrayList<>();


    public Usuario(String firstName, String lastName, Sex sex, String cpf, String birthdate, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.cpf = cpf;
        this.birthdate = birthdate;
        this.email = email;
        this.password = password;
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
