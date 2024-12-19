package com.github.syndexmx.socksbase.controllers;

import com.github.syndexmx.socksbase.services.SocksFilteredCollectionService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Получение количества носков по фильтру", description = "Позволяет узнать количество хранимых носков, удовлетворяющих критериям по цвету и составу")
    ResponseEntity<Long> getFilteredAmount(@RequestParam String colour,
                                           @RequestParam(required = false) Integer lessThan,
                                           @RequestParam(required = false) Integer moreThan,
                                           @RequestParam(required = false) Integer equal){
        if (equal != null ) {
            log.info("GET @api/socks : " + colour + " equal=" + equal.toString());
            return new ResponseEntity<>(socksFilteredCollectionService.getFilteredAmount(colour, equal),
                    HttpStatus.OK);
        }
        if (lessThan != null && moreThan !=null) {
            log.info("GET @api/socks : " + colour + " moreThan=" + moreThan.toString() +
                    " lessThan=" + lessThan.toString());
            return new ResponseEntity<>(socksFilteredCollectionService
                    .getFilteredAmount(colour, moreThan, lessThan),
                    HttpStatus.OK);
        }
        if (lessThan != null) {
            log.info("GET @api/socks : " + colour + " lessThan=" + lessThan.toString());
            return new ResponseEntity<>(socksFilteredCollectionService
                    .getFilteredAmount(colour, 0, lessThan),
                    HttpStatus.OK);
        }
        if (moreThan != null) {
            log.info("GET @api/socks : " + colour + " moreThan=" + moreThan.toString());
            return new ResponseEntity<>(socksFilteredCollectionService
                    .getFilteredAmount(colour, moreThan, 100),
                    HttpStatus.OK);
        }
        log.info("GET @api/socks : " + colour);
        return new ResponseEntity<>(socksFilteredCollectionService
                .getFilteredAmount(colour, 0, 100),
                HttpStatus.OK);
    }

}
