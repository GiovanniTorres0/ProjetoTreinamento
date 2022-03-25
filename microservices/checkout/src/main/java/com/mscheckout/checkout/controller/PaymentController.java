package com.mscheckout.checkout.controller;

import com.mscheckout.checkout.dto.PaymentDto;
import com.mscheckout.checkout.form.PaymentForm;
import com.mscheckout.checkout.services.service.PaymentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/v1")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @ApiOperation(value = "Busca todos os pagamentos", notes = "Busca todos os dados de pagamentos", response = PaymentController.class)
    @ApiResponses({@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 404, message = "Not Found")})
    @GetMapping(value = "/payments")
    public List<PaymentDto> getAll() {
        return paymentService.findAll();
    }

    @ApiOperation(value = "Cadastra o pagamento", notes = "Cadastra os dados do pagamento", response = PaymentController.class)
    @ApiResponses({@ApiResponse(code = 201, message = "CREATED"), @ApiResponse(code = 400, message = "Bad Request")})
    @PostMapping(value = "/payments")
    public ResponseEntity<PaymentDto> salvarPagamento(@RequestBody @Valid PaymentForm paymentForm) {
        return paymentService.salvarPagamento(paymentForm);
    }

    @ApiOperation(value = "Atualiza os Dados de pagamento", notes = "Atualiza os dados cadastrados", response = PaymentController.class)
    @ApiResponses({@ApiResponse(code = 202, message = "ACCEPTED"), @ApiResponse(code = 404, message = "Not Found")})
    @PutMapping("/payments/{id}")
    @Transactional
    public ResponseEntity<PaymentDto> atualizarPagamento(@PathVariable Integer id, @RequestBody @Valid PaymentForm paymentForm) {
        return paymentService.atualizarPagamento(id, paymentForm);
    }

    @ApiOperation(value = "Busca os dados por id", notes = "Busca os dados de pagamento por id", response = PaymentController.class)
    @ApiResponses({@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 404, message = "Not Found")})
    @GetMapping(value = "/payments/{id}")
    public ResponseEntity<PaymentDto> findById(@PathVariable Integer id) {
        return paymentService.findById(id);
    }

    @ApiOperation(value = "Deleta os dados por id ", notes = "Deleta os dados de pagamento por ID", response = PaymentController.class)
    @ApiResponses({@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 404, message = "Not Found")})
    @DeleteMapping("/payments/{id}")
    public ResponseEntity<PaymentDto> deleteById(@PathVariable Integer id) {
        return paymentService.deleteById(id);
    }



}
