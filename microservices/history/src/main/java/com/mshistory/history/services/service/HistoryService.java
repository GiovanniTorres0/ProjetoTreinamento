package com.mshistory.history.services.service;

import com.mshistory.history.dto.HistoryDto;
import org.springframework.web.bind.annotation.PathVariable;

public interface HistoryService {

   HistoryDto retorna(@PathVariable Integer idUser) throws Exception;
}
