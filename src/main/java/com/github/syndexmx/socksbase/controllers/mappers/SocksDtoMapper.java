package com.github.syndexmx.socksbase.controllers.mappers;

import com.github.syndexmx.socksbase.controllers.dtos.SocksDto;
import com.github.syndexmx.socksbase.model.Socks;

public class SocksDtoMapper {

    public static SocksDto socksToSocksDto(Socks socks){
        return SocksDto.builder()
                .typeId(socks.getTypeId())
                .colour(socks.getColour())
                .cotton(socks.getCotton())
                .amount(socks.getAmount())
                .build();
    }

    public static Socks socksDtoToSocks(SocksDto socksDto){
        return Socks.builder()
                .typeId(socksDto.getTypeId())
                .colour(socksDto.getColour())
                .cotton(socksDto.getCotton())
                .amount(socksDto.getAmount())
                .build();
    }
}
