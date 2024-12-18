package com.github.syndexmx.socksbase.repositories;

import com.github.syndexmx.socksbase.model.Socks;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SocksRepositoryService {

    Optional<Socks> findTypeByColourAndCotton(String colour, Integer cotton);
    Socks save(Socks socksToSave);
}
