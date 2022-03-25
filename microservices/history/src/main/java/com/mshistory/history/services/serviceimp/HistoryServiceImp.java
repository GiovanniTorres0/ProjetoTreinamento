package com.mshistory.history.services.serviceimp;

import com.mshistory.history.dto.HistoryDto;
import com.mshistory.history.repository.HistoryRepository;
import com.mshistory.history.services.sequence.SequenceGeneratorService;
import com.mshistory.history.services.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class HistoryServiceImp implements HistoryService {

    @Autowired
    HistoryRepository historyRepository;

    @Autowired
    SequenceGeneratorService sequenceGenerator;


    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss a");
    LocalDateTime now = LocalDateTime.now();



    @Override
    public HistoryDto retorna(@PathVariable Integer idUser) throws Exception {
        try {
                return null;
        } catch (Exception e) {
            throw new Exception("ERRO, POSSIVELMENTE NÃO EXISTE USUARIO, PAGAMENTO E PRODUTOS PARA RETORNAR");
        }
    }


}
