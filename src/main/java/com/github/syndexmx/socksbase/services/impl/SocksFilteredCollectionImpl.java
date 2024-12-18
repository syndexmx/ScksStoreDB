package com.github.syndexmx.socksbase.services.impl;

import com.github.syndexmx.socksbase.repositories.SocksRepositoryService;
import com.github.syndexmx.socksbase.services.SocksFilteredCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SocksFilteredCollectionImpl implements SocksFilteredCollectionService {

    private final SocksRepositoryService socksRepositoryService;

    public SocksFilteredCollectionImpl(@Autowired SocksRepositoryService socksRepositoryService) {
        this.socksRepositoryService = socksRepositoryService;
    }

    @Override
    public Long getFilteredAmount(String colour, int moreThan, int lessThan) {
        return socksRepositoryService.getMatchingAmount(colour, moreThan, lessThan);
    }

    @Override
    public Long getFilteredAmount(String colour, int equal) {
        return socksRepositoryService.getExactlyMatchingAmount(colour, equal);
    }
}
