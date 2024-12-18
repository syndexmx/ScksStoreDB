package com.github.syndexmx.socksbase.repositories;

import com.github.syndexmx.socksbase.model.Socks;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SocksRepositoryService {

    Optional<Socks> findTypeByColourAndCotton(String colour, Integer cotton);
    Socks save(Socks socksToSave);
    Long getMatchingAmount(String colour, int moreThan, int lessThan);
    Long getExactlyMatchingAmount(String colour, int equal);
    List<Socks> getMatchingList(int moreThan, int lessThan);
    List<Socks> getExactlyMatchingList(Integer equal);
}
