package com.github.syndexmx.socksbase.controllers;

import com.github.syndexmx.socksbase.controllers.dtos.SocksDto;
import com.github.syndexmx.socksbase.services.CorrectionSocksService;
import io.swagger.annotations.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.github.syndexmx.socksbase.controllers.mappers.SocksDtoMapper.socksDtoToSocks;
import static com.github.syndexmx.socksbase.controllers.mappers.SocksDtoMapper.socksToSocksDto;

/**
 * @author syndexmx
 */

@RestController
@RequestMapping
@Slf4j
public class CorrectionSocksController {

    private final CorrectionSocksService correctionSocksService;

    public CorrectionSocksController(@Autowired CorrectionSocksService correctionSocksService) {
        this.correctionSocksService = correctionSocksService;
    }

    @PutMapping(path = "api/socks/{id}")
    @Operation(summary = "Корректировка записи", description = "Позволяет напрямую поправить цвет, состав и количество")
    ResponseEntity<Object> correctSocks(@PathVariable Long id,
                                        @RequestBody SocksDto socksDto) {
        log.info("PUT @api/socks/" + id.toString() + ": " + socksDto.toString());
        socksDto.setTypeId(id);
        SocksDto resultDto = socksToSocksDto(correctionSocksService
                .correctSocks(socksDtoToSocks(socksDto)));
        return new ResponseEntity<>(resultDto, HttpStatus.ACCEPTED);

    }

}
