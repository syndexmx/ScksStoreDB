package com.github.syndexmx.socksbase.services.impl;

import com.github.syndexmx.socksbase.model.Socks;
import com.github.syndexmx.socksbase.repositories.SocksRepositoryService;
import com.github.syndexmx.socksbase.services.CorrectionSocksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CorrectionSocksServiceImpl implements CorrectionSocksService {

    private final SocksRepositoryService auxRepository;

    public CorrectionSocksServiceImpl(@Autowired SocksRepositoryService auxRepository) {
        this.auxRepository = auxRepository;
    }

    @Override
    public Socks correctSocks(Socks socksToFix) {
        Socks savedSocks = auxRepository.save(socksToFix);
        return savedSocks;
    }
}
