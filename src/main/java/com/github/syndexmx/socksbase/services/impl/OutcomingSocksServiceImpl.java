package com.github.syndexmx.socksbase.services.impl;

import com.github.syndexmx.socksbase.aspects.exceptionhandling.CustomHttpResponseException;
import com.github.syndexmx.socksbase.model.Socks;
import com.github.syndexmx.socksbase.repositories.SocksRepositoryService;
import com.github.syndexmx.socksbase.services.OutcomingSocksService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Slf4j
public class OutcomingSocksServiceImpl implements OutcomingSocksService {


    private final SocksRepositoryService auxRepository;

    public OutcomingSocksServiceImpl(@Autowired SocksRepositoryService auxRepository) {
        this.auxRepository = auxRepository;
    }

    @Override
    @Transactional
    public Socks removeSocks(Socks outgoingSocks) {
        log.info("OutcomingSocksServiceImpl : removeSocks" + outgoingSocks.toString());
        Optional<Socks> storedSocksOption = auxRepository
                .findTypeByColourAndCotton(outgoingSocks.getColour(), outgoingSocks.getCotton());
        if (storedSocksOption.isEmpty()){
            throw new CustomHttpResponseException(HttpStatus.NOT_FOUND, "Нет такого типа носков");
        }
        Socks storedSocks = storedSocksOption.get();
        Socks socksToSave = storedSocks.remove(outgoingSocks.getAmount());
        Socks savedSocks = auxRepository.save((socksToSave));
        return savedSocks;
    }
}
