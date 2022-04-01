package com.mshistory.history.services.service;


import com.mshistory.history.dto.HistoryDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

public interface HistoryService {

    ResponseEntity<HistoryDto> buscaHistoricoUsuario(@PathVariable Integer id);
}
