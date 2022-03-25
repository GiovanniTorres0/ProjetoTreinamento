package com.mshistory.history.controller;

import com.mshistory.history.dto.HistoryDto;
import com.mshistory.history.services.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HistoryController {


    @Autowired
    HistoryService historyService;

    @GetMapping("/v1/history/{userId}")
    public HistoryDto retorna(@PathVariable("userId") Integer userID) throws Exception {
        return historyService.retorna(userID);
    }


}
