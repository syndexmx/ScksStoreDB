package com.github.syndexmx.socksbase.repositories.mappers;

import com.github.syndexmx.socksbase.model.Socks;
import com.github.syndexmx.socksbase.repositories.entities.SocksEntity;
import org.junit.jupiter.api.Test;

import static com.github.syndexmx.socksbase.model.TestSocks.testSocks;
import static com.github.syndexmx.socksbase.repositories.entities.TestSocksEntity.testSocksEntity;
import static org.junit.jupiter.api.Assertions.*;

class SocksEntityMapperTest {

    @Test
    void testSocksToSocksEntity() {
        Socks socks = testSocks;
        SocksEntity socksDto = SocksEntity.builder()
                .typeId(socks.getTypeId())
                .colour(socks.getColour())
                .cotton(socks.getCotton())
                .amount(socks.getAmount())
                .build();
        SocksEntity expectedEntity = testSocksEntity;
        assertEquals(expectedEntity, socksDto);
    }

    @Test
    void testSocksEntityToSocks() {
        SocksEntity socksEntity = testSocksEntity;
        Socks socks = Socks.builder()
                .typeId(socksEntity.getTypeId())
                .colour(socksEntity.getColour())
                .cotton(socksEntity.getCotton())
                .amount(socksEntity.getAmount())
                .build();
        Socks expected = testSocks;
        assertEquals(expected, socks);
    }
}