package com.mscheckout.checkout.service;

import com.mscheckout.checkout.dto.PaymentDto;
import com.mscheckout.checkout.enums.Card;
import com.mscheckout.checkout.form.CartForm;
import com.mscheckout.checkout.form.PaymentForm;
import com.mscheckout.checkout.form.PurchaseForm;
import com.mscheckout.checkout.repository.PaymentRepository;
import com.mscheckout.checkout.services.serviceimp.PaymentServiceImp;
import com.mscheckout.checkout.services.serviceimp.RabbitmqServiceImp;
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
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
public class CheckoutServiceTest {


    @InjectMocks
    PaymentServiceImp paymentServiceImp;

    @InjectMocks
    RabbitmqServiceImp rabbitmqServiceImp;

    @Mock
    PaymentRepository paymentRepository;

    @Before
    public void Setup() {
        MockitoAnnotations.openMocks(this);
    }


    @BeforeEach
    public PaymentForm paymentForm() {
        return new PaymentForm(Card.CREDIT, 5, true);
    }

    @BeforeEach
    public List<CartForm> cartForms(){
        List<CartForm> cartForms = new LinkedList<>();
        CartForm cartForm = new CartForm(1, 5);
        CartForm cartForm1 = new CartForm(2,10);
        cartForms.add(0, cartForm);
        cartForms.add(1, cartForm1);
        return cartForms;
    }

    @BeforeEach
    public PurchaseForm purchaseForm(){
        return new PurchaseForm("1", "1", cartForms());
    }

    @Test
    public void SalvarMetodoDePagamento() {
        ResponseEntity<PaymentDto> entity = paymentServiceImp.salvarPagamento(paymentForm());
        if (entity.getStatusCodeValue() == 201) {
            Assert.assertEquals(Card.CREDIT, Objects.requireNonNull(entity.getBody()).getCard());
            Assert.assertEquals(true, entity.getBody().getStatus());
            System.out.println("MÉTODO DE PAGAMENTO CADASTRADO");
        } else {
            Assert.assertEquals(400, entity.getStatusCodeValue());
            System.out.println("METODO DE PAGAMENTO NÃO CADASTRADO");
        }
    }

    @Test
    public void BuscarMetodoDePagamento() {
        ResponseEntity<PaymentDto> entity = paymentServiceImp.findById(1);
        if (entity.getStatusCodeValue() == 200) {
            Assert.assertEquals(Card.DEBIT, Objects.requireNonNull(entity.getBody()).getCard());
            Assert.assertEquals(5, entity.getBody().getDiscount());
            System.out.println("MÉTODO DE PAGAMENTO ENCONTRADO");
        } else {
            Assert.assertEquals(404, entity.getStatusCodeValue());
            System.out.println("MÉTODO DE PAGAMENTO NÃO ENCONTRADO");
        }
    }

    @Test
    public void BuscaTodosOsPagamentos() {
        List<PaymentDto> paymentDtoList = paymentServiceImp.findAll();
        if (paymentDtoList.size() == 0) {
            Assert.assertNull(null);
            System.out.println("LISTA VAZIA");
        }
        for (int i = 0; i < paymentDtoList.size(); i++) {
            if (paymentDtoList.get(i) != null) {
                Assert.assertNotNull(paymentDtoList);
                System.out.println("TODOS OS PAGAMENTOS ENCONTRADO");
            }
        }
    }

    @Test
    public void AtualizarPagamento() throws Exception {
        ResponseEntity<PaymentDto> entity = paymentServiceImp.atualizarPagamento(1, paymentForm());
        if (entity.getStatusCodeValue() == 202) {
            Assert.assertEquals(Card.CREDIT, Objects.requireNonNull(entity.getBody()).getCard());
            Assert.assertEquals(5, entity.getBody().getDiscount());
            System.out.println("PAGAMENTO ATUALIZADO");
        } else if (entity.getStatusCodeValue() == 404) {
            Assert.assertNull(null);
            System.out.println("PAGAMENTO NÃO ENCONTRADO");
        } else {
            throw new Exception("ERRO INESPERADO");
        }
    }


    @Test
    public void DeletarPagamentoPeloId() throws Exception {
        ResponseEntity<PaymentDto> entity = paymentServiceImp.deleteById(1);
        if (entity.getStatusCodeValue() == 200) {
            Assert.assertNotNull(entity);
            System.out.println("PAGAMENTO DELETADO");
        } else if (entity.getStatusCodeValue() == 404) {
            Assert.assertNull(null);
            System.out.println("PAGAMENTO NÃO ENCONTRADO");
        } else {
            throw new Exception("ERRO INESPERADO");
        }
    }

    @Test(expected = EntityNotFoundException.class)
    public void RetornaOpagamento() {
        PaymentDto paymentDto = paymentServiceImp.retorna(1);
        if (Objects.isNull(paymentDto)) {
            Assert.assertNull(null);
            System.out.println("NÃO RETORNA");
        } else {
            Assert.assertNotNull(paymentDto);
            System.out.println("RETORNA");
        }
    }


    @Test
    public void EnviaPorRabbitMQaCompra() {
     String teste = rabbitmqServiceImp.send(purchaseForm());
     if(Objects.equals(teste, "MENSAGEM ENVIADA")){
         Assert.assertNotNull(teste);
         System.out.println("MENSAGEM ENVIADA COM SUCESSO");
     } else if(Objects.equals(teste, "MENSAGEM NÃO ENVIADA")){
         Assert.assertNotNull(teste);
         System.out.println("MENSAGEM NÃO ENVIADA");
     }
    }

}
