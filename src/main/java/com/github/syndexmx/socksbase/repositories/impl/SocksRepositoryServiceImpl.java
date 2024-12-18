package com.github.syndexmx.socksbase.repositories.impl;

import com.github.syndexmx.socksbase.model.Socks;
import com.github.syndexmx.socksbase.repositories.SocksRepositoryService;
import com.github.syndexmx.socksbase.repositories.SocksRepository;
import com.github.syndexmx.socksbase.repositories.entities.SocksEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.github.syndexmx.socksbase.repositories.mappers.SocksEntityMapper.socksEntityToSocks;
import static com.github.syndexmx.socksbase.repositories.mappers.SocksEntityMapper.socksToSocksEntity;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

@Repository
public class SocksRepositoryServiceImpl implements SocksRepositoryService {

    private final SocksRepository socksRepository;

    public SocksRepositoryServiceImpl(@Autowired SocksRepository socksRepository) {
        this.socksRepository = socksRepository;
    }


    @Override
    public Optional<Socks> findTypeByColourAndCotton(String colour, Integer cotton) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("colour", exact())
                .withMatcher("cotton", exact())
                .withIgnorePaths("type_id", "amount");
        Example<SocksEntity> example = Example.of(SocksEntity.builder()
                .cotton(cotton)
                .colour(colour)
                .build(), matcher);
        Optional<SocksEntity> storedSocksEntityOption = socksRepository.findOne(example);
        Optional<Socks> optionalSocks = Optional.ofNullable(socksEntityToSocks(storedSocksEntityOption
                .orElse(null)));
        return optionalSocks;
    }

    @Override
    public Socks save(Socks socksToSave) {
        return socksEntityToSocks(socksRepository.save(socksToSocksEntity(socksToSave)));
    }

    @Override
    public Long getMatchingAmount(String colour, int moreThan, int lessThan) {
        return socksRepository.getMatchingAmount(colour, moreThan, lessThan);
    }

    @Override
    public Long getExactlyMatchingAmount(String colour, int equal) {
        return socksRepository.getExactlyMatchingAmount(colour, equal);
    }
}
