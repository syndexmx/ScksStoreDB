package com.github.syndexmx.socksbase.services.impl;

import com.github.syndexmx.socksbase.model.Socks;
import com.github.syndexmx.socksbase.repositories.SocksRepositoryService;
import com.github.syndexmx.socksbase.services.SocksFilteredCollectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SocksFilteredCollectionImpl implements SocksFilteredCollectionService {

    private final SocksRepositoryService socksRepositoryService;

    public SocksFilteredCollectionImpl(@Autowired SocksRepositoryService socksRepositoryService) {
        this.socksRepositoryService = socksRepositoryService;
    }

    @Override
    public Long getFilteredAmount(String colour, int moreThan, int lessThan) {
        log.info("SocksFilteredCollectionImpl : getFilteredAmount" + colour + " " + moreThan + " " + lessThan);
        return socksRepositoryService.getMatchingAmount(colour, moreThan, lessThan);
    }

    @Override
    public Long getFilteredAmount(String colour, int equal) {
        log.info("SocksFilteredCollectionImpl : getFilteredAmount" + colour + " " + equal);
        return socksRepositoryService.getExactlyMatchingAmount(colour, equal);
    }

    @Override
    public List<Socks> getFilteredList(int moreThan, int lessThan) {
        log.info("SocksFilteredCollectionImpl : getFilteredList " + moreThan + " " + lessThan);
        return socksRepositoryService.getMatchingList(moreThan, lessThan);
    }

    @Override
    public List<Socks> getFilteredList(Integer equal) {
        log.info("SocksFilteredCollectionImpl : getFilteredList " + equal);
        return socksRepositoryService.getExactlyMatchingList(equal);
    }
}
