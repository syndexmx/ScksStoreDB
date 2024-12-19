package com.github.syndexmx.socksbase.controllers;

import com.github.syndexmx.socksbase.services.SocksFilteredCollectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@Slf4j
public class GetFilteredAmountController {

    private final SocksFilteredCollectionService socksFilteredCollectionService;

    public GetFilteredAmountController(@Autowired SocksFilteredCollectionService socksFilteredCollectionService) {
        this.socksFilteredCollectionService = socksFilteredCollectionService;
    }

    @GetMapping(path = "api/socks")
    ResponseEntity<Long> getFilteredAmount(@RequestParam String colour,
                                           @RequestParam(required = false) Integer lessThan,
                                           @RequestParam(required = false) Integer moreThan,
                                           @RequestParam(required = false) Integer equal){
        if (equal != null ) {
            return new ResponseEntity<>(socksFilteredCollectionService.getFilteredAmount(colour, equal),
                    HttpStatus.OK);
        }
        if (lessThan != null && moreThan !=null) {
            return new ResponseEntity<>(socksFilteredCollectionService
                    .getFilteredAmount(colour, moreThan, lessThan),
                    HttpStatus.OK);
        }
        if (lessThan != null) {
            return new ResponseEntity<>(socksFilteredCollectionService
                    .getFilteredAmount(colour, 0, lessThan),
                    HttpStatus.OK);
        }
        if (moreThan != null) {
            return new ResponseEntity<>(socksFilteredCollectionService
                    .getFilteredAmount(colour, moreThan, 100),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(socksFilteredCollectionService
                .getFilteredAmount(colour, 0, 100),
                HttpStatus.OK);
    }

}