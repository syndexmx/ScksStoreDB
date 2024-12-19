package com.github.syndexmx.socksbase.services.impl;

import com.github.syndexmx.socksbase.model.Socks;
import com.github.syndexmx.socksbase.repositories.SocksRepositoryService;
import com.github.syndexmx.socksbase.services.CorrectionSocksService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CorrectionSocksServiceImpl implements CorrectionSocksService {

    private final SocksRepositoryService auxRepository;

    public CorrectionSocksServiceImpl(@Autowired SocksRepositoryService auxRepository) {
        this.auxRepository = auxRepository;
    }

    @Override
    public Socks correctSocks(Socks socksToFix) {
        log.info("CorrectionSocksServiceImpl : correctSocks" + socksToFix.toString());
        Socks savedSocks = auxRepository.save(socksToFix);
        return savedSocks;
    }
}
