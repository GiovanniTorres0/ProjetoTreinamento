package br.com.mscustomer.form;


import br.com.mscustomer.config.validation.ValidCPF;
import br.com.mscustomer.enums.Sex;
import br.com.mscustomer.models.User;
import br.com.mscustomer.repository.UserRepository;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Optional;

@Getter
@Setter
public class UserForm {

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
    private Sex sex;
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
    private Date birthdate;

    public UserForm() {

    }

    public UserForm(String firstName, String lastName, Sex sex, String email, String cpf, String password, Date birthdate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.email = email;
        this.cpf = cpf;
        this.password = password;
        this.birthdate = birthdate;
    }


    //Validação de email
    private boolean isNotPresentEmail(String email, UserRepository userRepository) {
        Optional<User> EmailValid = userRepository.findByEmail(email);
        if (EmailValid.isPresent()) {
            return false;
        }
        return true;
    }

    //Validação de CPF
    private void cpf(String cpf) {
        try {
            if (ValidCPF.isCPF(cpf) == false) {
            cpf = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

