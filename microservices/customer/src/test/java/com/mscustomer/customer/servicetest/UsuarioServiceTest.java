package com.mscustomer.customer.servicetest;

import com.mscustomer.customer.dto.UsuarioDto;
import com.mscustomer.customer.entity.Usuario;
import com.mscustomer.customer.enums.Sex;
import com.mscustomer.customer.form.AtualizarUsuarioForm;
import com.mscustomer.customer.form.UsuarioForm;
import com.mscustomer.customer.repository.UsuarioRepository;
import com.mscustomer.customer.services.serviceimp.UsuarioServiceImp;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.persistence.EntityNotFoundException;
import java.text.ParseException;
import java.util.Objects;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
public class UsuarioServiceTest {

    @InjectMocks
    UsuarioServiceImp usuarioServiceImp;
    @Mock
    UsuarioRepository usuarioRepository;
    @Mock
    Usuario usuario;
    @Before
    public void Setup(){
        MockitoAnnotations.openMocks(this);
    }

    @BeforeEach
    public UsuarioForm usuarioForm(){
        return new UsuarioForm("Pedro", "Cardozo", Sex.MASCULINO, "aaaaae@gmail.com", "448.395.830.56", "102030405", "1998-10-11");
    }

    @BeforeEach
    public AtualizarUsuarioForm atualizarUsuarioForm(){
        return new AtualizarUsuarioForm("Carloz", "Torres", Sex.MASCULINO, "eeeeea@gmail.com", "420.191.230.29", "152035462", "1840-07-07");
    }

    @BeforeEach
    @Test
    public void Save() throws Exception {
        usuarioServiceImp.save(usuarioForm());
        System.out.println("Objeto foi salvo");
    }

    @Test
    public void buscaPorId(){
        Integer id = 1;
        ResponseEntity<UsuarioDto> entity = usuarioServiceImp.findById(id);
        if(entity.getStatusCodeValue() == 404){
            Assert.assertEquals(entity.getStatusCodeValue(), 404);
            System.out.println("Entidade não foi encontrada");
        } else {
            Assert.assertEquals("Pedro", Objects.requireNonNull(entity.getBody()).getFirstName());
            System.out.println("Entidade foi encontrada");
        }
    }

    @Test
    public void atualizarPorId() throws ParseException {
        Integer id = 1;
        ResponseEntity<UsuarioDto> entity = usuarioServiceImp.update(atualizarUsuarioForm(),id);
        if(entity.getStatusCodeValue() == 404) {
            Assert.assertEquals(entity.getStatusCodeValue(), 404);
            System.out.println("Entidade não foi encontrada para atualizar");
        } else {
            Assert.assertEquals("Carloz", Objects.requireNonNull(entity.getBody()).getFirstName());
            System.out.println("Entidade foi encontrada para atualizar");
        }
    }

    @Test(expected = EntityNotFoundException.class)
    public void RetornaUsuario(){
        Integer id = 1;
        UsuarioDto usuarioDto = usuarioServiceImp.retorna(id);
    }

}
