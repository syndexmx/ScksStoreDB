package com.github.syndexmx.socksbase.controllers;

import com.github.syndexmx.socksbase.controllers.dtos.SocksDto;
import com.github.syndexmx.socksbase.services.SocksFilteredCollectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.github.syndexmx.socksbase.controllers.mappers.SocksDtoMapper.socksToSocksDto;
import static com.github.syndexmx.socksbase.repositories.mappers.SocksEntityMapper.socksEntityToSocks;

@RestController
@RequestMapping
@Slf4j
public class GetFilteredListController {

    private final SocksFilteredCollectionService socksFilteredCollectionService;

    public GetFilteredListController(@Autowired SocksFilteredCollectionService socksFilteredCollectionService) {
        this.socksFilteredCollectionService = socksFilteredCollectionService;
    }

    @GetMapping(path = "api/socks/filtered")
    ResponseEntity<List<SocksDto>> getFilteredList(@RequestParam(required = false) Integer lessThan,
                                                   @RequestParam(required = false) Integer moreThan,
                                                   @RequestParam(required = false) Integer equal){
        if (equal != null ) {
            log.info("GET @api/socks/filtered : equal=" + equal.toString());
            return new ResponseEntity<>(
                    socksFilteredCollectionService.getFilteredList(equal).stream()
                            .map(socks -> socksToSocksDto(socks)).toList(),
                    HttpStatus.OK);
        }
        if (lessThan != null && moreThan !=null) {
            log.info("GET @api/socks/filtered : moreThan=" + moreThan.toString() +
                    " lessThan=" + lessThan.toString());
            return new ResponseEntity<>(socksFilteredCollectionService
                    .getFilteredList(moreThan, lessThan).stream()
                    .map(socks -> socksToSocksDto(socks)).toList(),
                    HttpStatus.OK);
        }
        if (lessThan != null) {
            log.info("GET @api/socks/filtered : lessThan=" + lessThan.toString());
            return new ResponseEntity<>(socksFilteredCollectionService
                    .getFilteredList(0, lessThan).stream()
                    .map(socks -> socksToSocksDto(socks)).toList(),
                    HttpStatus.OK);
        }
        if (moreThan != null) {
            log.info("GET @api/socks/filtered : moreThan=" + moreThan.toString());
            return new ResponseEntity<>(socksFilteredCollectionService
                    .getFilteredList(moreThan, 100).stream()
                    .map(socks -> socksToSocksDto(socks)).toList(),
                    HttpStatus.OK);
        }
        log.info("GET @api/socks/filtered : no limits");
        return new ResponseEntity<>(socksFilteredCollectionService
                .getFilteredList(0, 100).stream()
                .map(socks -> socksToSocksDto(socks)).toList(),
                HttpStatus.OK);
    }

}
