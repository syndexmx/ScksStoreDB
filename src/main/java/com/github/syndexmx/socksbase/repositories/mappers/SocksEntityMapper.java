package com.github.syndexmx.socksbase.repositories.mappers;

import com.github.syndexmx.socksbase.model.Socks;
import com.github.syndexmx.socksbase.repositories.entities.SocksEntity;

public class SocksEntityMapper {

    public static SocksEntity socksToSocksEntity(Socks socks){
        if (socks == null) return null;
        return SocksEntity.builder()
                .typeId(socks.getTypeId())
                .colour(socks.getColour())
                .cotton(socks.getCotton())
                .amount(socks.getAmount())
                .build();
    }

    public static Socks socksEntityToSocks(SocksEntity socksEntity){
        if (socksEntity == null) return null;
        return Socks.builder()
                .typeId(socksEntity.getTypeId())
                .colour(socksEntity.getColour())
                .cotton(socksEntity.getCotton())
                .amount(socksEntity.getAmount())
                .build();
    }

}
