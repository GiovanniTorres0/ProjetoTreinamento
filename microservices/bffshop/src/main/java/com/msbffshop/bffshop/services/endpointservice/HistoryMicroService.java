package com.msbffshop.bffshop.services.endpointservice;

import com.mshistory.history.dto.HistoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "history", url = "http://localhost:8084/v1")
public interface HistoryMicroService {


    @RequestMapping(method = RequestMethod.GET, value = "/historic/user/{id}")
    ResponseEntity<HistoryDto> buscaHistorico(@PathVariable(value = "id") Integer id);

}
