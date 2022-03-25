package com.mscustomer.customer.entity;

import com.mscustomer.customer.enums.Sex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_Usuario")
public class Usuario implements Serializable {

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



    public Usuario(String firstName, String lastName, Sex sex, String cpf, String birthdate, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.cpf = cpf;
        this.birthdate = birthdate;
        this.email = email;
        this.password = password;
    }

}
