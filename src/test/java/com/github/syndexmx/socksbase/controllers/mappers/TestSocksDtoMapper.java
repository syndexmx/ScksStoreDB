package com.github.syndexmx.socksbase.controllers.mappers;

import com.github.syndexmx.socksbase.controllers.dtos.SocksDto;
import com.github.syndexmx.socksbase.model.Socks;
import org.junit.jupiter.api.Test;

import static com.github.syndexmx.socksbase.controllers.dtos.TestSocksDto.testSocksDto;
import static com.github.syndexmx.socksbase.model.TestSocks.testSocks;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSocksDtoMapper {

    @Test
    void testSocksToSocksDto() {
        Socks socks = testSocks;
        SocksDto socksDto = SocksDto.builder()
                .typeId(socks.getTypeId())
                .colour(socks.getColour())
                .cotton(socks.getCotton())
                .amount(socks.getAmount())
                .build();
        SocksDto expectedDto = testSocksDto;
        assertEquals(expectedDto, socksDto);
    }

    @Test
    void testSocksDtoToSocks() {
        SocksDto socksDto = testSocksDto;
        Socks socks = Socks.builder()
                .typeId(socksDto.getTypeId())
                .colour(socksDto.getColour())
                .cotton(socksDto.getCotton())
                .amount(socksDto.getAmount())
                .build();
        Socks expected = testSocks;
        assertEquals(expected, socks);
    }
}
