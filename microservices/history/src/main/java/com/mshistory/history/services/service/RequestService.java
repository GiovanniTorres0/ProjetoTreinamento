package com.mshistory.history.services.service;

import com.mscatalog.catalog.dto.ProdutosVariadosDto;
import com.mscheckout.checkout.dto.PaymentDto;
import com.mscustomer.customer.dto.UsuarioDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
@Service
public class RequestService {

    @FeignClient(name = "usuario", url= "http://localhost:8080/v1")
    public interface RequestCostumer {
        @RequestMapping(method = RequestMethod.GET, value = "/users/{id}")
        ResponseEntity<UsuarioDto> buscaUsuarioPorId(@PathVariable(value = "id") Integer id);
    }

    @FeignClient(name = "pagamento", url= "http://localhost:8083/v1")
    public interface RequestCheckout {
        @RequestMapping(method = RequestMethod.GET, value = "/payments")
        List<PaymentDto> buscaTodosOsPagamentos();
    }

    @FeignClient(name = "catalog", url = "http://localhost:8082/v1")
    public interface ResquestCatalogService {
        @RequestMapping(method = RequestMethod.GET, value = "/products")
        List<ProdutosVariadosDto> buscaProdutos();
    }


}
