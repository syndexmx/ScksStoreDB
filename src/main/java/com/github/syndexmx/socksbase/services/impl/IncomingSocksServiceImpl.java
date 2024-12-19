package com.github.syndexmx.socksbase.services.impl;

import com.github.syndexmx.socksbase.model.Socks;
import com.github.syndexmx.socksbase.repositories.SocksRepositoryService;
import com.github.syndexmx.socksbase.services.IncomingSocksService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Slf4j
public class IncomingSocksServiceImpl implements IncomingSocksService {

    private final SocksRepositoryService auxRepository;

    public IncomingSocksServiceImpl(@Autowired SocksRepositoryService auxRepository) {
        this.auxRepository = auxRepository;
    }

    @Override
    @Transactional
    public Socks addSocks(Socks incomingSocks) {
        log.info("IncomingSocksServiceImpl : addSocks" + incomingSocks.toString());
        Optional<Socks> storedSocksOption = auxRepository
                .findTypeByColourAndCotton(incomingSocks.getColour(), incomingSocks.getCotton());
        if (storedSocksOption.isEmpty()){
            return auxRepository.save((incomingSocks));
        }
        Socks storedSocks = storedSocksOption.get();
        Socks socksToSave = storedSocks.add(incomingSocks.getAmount());
        Socks savedSocks = auxRepository.save((socksToSave));
        return savedSocks;
    }
}
