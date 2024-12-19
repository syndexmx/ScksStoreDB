package com.github.syndexmx.socksbase.controllers;

import com.github.syndexmx.socksbase.controllers.dtos.SocksDto;
import com.github.syndexmx.socksbase.model.Socks;
import com.github.syndexmx.socksbase.services.IncomingSocksService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.github.syndexmx.socksbase.controllers.mappers.SocksDtoMapper.socksDtoToSocks;
import static com.github.syndexmx.socksbase.controllers.mappers.SocksDtoMapper.socksToSocksDto;

@RestController
@RequestMapping
@Slf4j
public class IncomingSocksController {

    private final IncomingSocksService incomingSocksService;

    public IncomingSocksController(@Autowired IncomingSocksService incomingSocksService) {
        this.incomingSocksService = incomingSocksService;
    }

    @PostMapping(path = "api/socks/income")
    @Operation(summary = "Добавление партии", description = "Позволяет добавить партию к уже хранимым по цвету и составу")
    ResponseEntity<Object> socksIn(@RequestBody SocksDto socksDto) {
        log.info("POST @api/socks/income : " + socksDto.toString());
        Socks incomingSocks = socksDtoToSocks(socksDto);
        try {
            SocksDto returnedSocksDto =
                    socksToSocksDto(incomingSocksService.addSocks(incomingSocks));
            return new ResponseEntity<>(returnedSocksDto, HttpStatus.OK);
        } catch (Exception e){
            log.warn("Incoming controller." + e.getMessage());
            Object responseObject = (Object)e.getMessage().toString();
            return new ResponseEntity<>(responseObject, HttpStatus.NOT_MODIFIED);
        }
    }

}
