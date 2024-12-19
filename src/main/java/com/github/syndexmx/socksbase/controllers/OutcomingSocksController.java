package com.github.syndexmx.socksbase.controllers;

import com.github.syndexmx.socksbase.controllers.dtos.SocksDto;
import com.github.syndexmx.socksbase.model.Socks;
import com.github.syndexmx.socksbase.services.OutcomingSocksService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.github.syndexmx.socksbase.controllers.mappers.SocksDtoMapper.socksDtoToSocks;
import static com.github.syndexmx.socksbase.controllers.mappers.SocksDtoMapper.socksToSocksDto;

@RestController
@RequestMapping
@Slf4j
public class OutcomingSocksController {

    private final OutcomingSocksService outcomingSocksService;

    public OutcomingSocksController(@Autowired OutcomingSocksService outcomingSocksService) {
        this.outcomingSocksService = outcomingSocksService;
    }

    @PostMapping(path = "api/socks/outcome")
    ResponseEntity<Object> socksIn(@RequestBody SocksDto socksDto) {
        log.info("POST @api/socks/outcome : " + socksDto.toString());
        Socks outgoingSocks = socksDtoToSocks(socksDto);
        try {
            SocksDto returnedSocksDto =
                    socksToSocksDto(outcomingSocksService.removeSocks(outgoingSocks));
            return new ResponseEntity<>(returnedSocksDto, HttpStatus.OK);
        } catch (Exception e){
            Object responseObject = (Object)e.getMessage().toString();
            return new ResponseEntity<>(responseObject, HttpStatus.NOT_MODIFIED);
        }
    }

}
