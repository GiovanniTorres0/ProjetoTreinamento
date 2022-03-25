package com.mscustomer.customer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "perfil")
public class Perfil implements GrantedAuthority {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "perfil_id_sequence",
            sequenceName = "perfil_id_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "perfil_id_sequence")
    private Integer id;
    private String email;


    @Override
    public String getAuthority() {
        return email;
    }
}
