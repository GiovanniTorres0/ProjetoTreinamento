package br.com.mscustomer.models;

import br.com.mscustomer.enums.Sex;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "TB_USER")
public class User implements Serializable {


    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private Sex sex;
    private Long cpf;
    private Date birthdate;
    private String email;
    private String password;
    private Boolean active;

    public User() {

    }

    public User(String firstName, String lastName, Sex sex, Long cpf, Date birthdate, String email, String password, Boolean active) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.cpf = cpf;
        this.birthdate = birthdate;
        this.email = email;
        this.password = password;
        this.active = active;
    }

}
