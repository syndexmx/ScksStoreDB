package com.github.syndexmx.socksbase.controllers;

import com.github.syndexmx.socksbase.services.SocksFilteredCollectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@Slf4j
public class GetFilteredSocksController {

    private final SocksFilteredCollectionService socksFilteredCollectionService;

    public GetFilteredSocksController(@Autowired SocksFilteredCollectionService socksFilteredCollectionService) {
        this.socksFilteredCollectionService = socksFilteredCollectionService;
    }

    @GetMapping(path = "api/socks")
    Long getFilteredAmount(@RequestParam String colour,
                           @RequestParam Integer lessThan,
                           @RequestParam Integer moreThan){

         return socksFilteredCollectionService.getFilteredAmount(colour, moreThan, lessThan);

    }

}
