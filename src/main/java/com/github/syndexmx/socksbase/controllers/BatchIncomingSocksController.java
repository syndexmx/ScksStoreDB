package com.github.syndexmx.socksbase.controllers;

import com.github.syndexmx.socksbase.model.Socks;
import com.github.syndexmx.socksbase.services.IncomingSocksService;
import io.swagger.annotations.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.github.syndexmx.socksbase.controllers.csvtools.csvParser.getAllSocksFromBatch;

@RestController
@RequestMapping
@Slf4j
public class BatchIncomingSocksController {

    private final IncomingSocksService incomingSocksService;

    public BatchIncomingSocksController(@Autowired IncomingSocksService incomingSocksService) {
        this.incomingSocksService = incomingSocksService;
    }

    @PostMapping(path = "api/socks/batch")
    @Operation(summary = "Групповое добавление их файла", description = "Позволяет добавить несколько партий из загруженного файла .CSV")
    ResponseEntity<Object> batchIncome(@RequestBody String csvBody) {
        log.info("POST @api/socks/batch : " + csvBody.toString());
        List<Socks> incomingSocksList = getAllSocksFromBatch(csvBody);
        List<Socks> updatedList = incomingSocksList.stream().map(
                (Socks socks) -> {
                    return incomingSocksService.addSocks(socks);
                }).toList();
        return new ResponseEntity<>(updatedList, HttpStatus.ACCEPTED);
    }

}
