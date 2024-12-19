package com.github.syndexmx.socksbase.repositories.entities;

import com.github.syndexmx.socksbase.controllers.dtos.SocksDto;

public class TestSocksEntity {
    public static SocksEntity testSocksEntity = SocksEntity.builder()
            .typeId(3L)
            .colour("yellow")
            .cotton(90)
            .amount(16)
            .build();

    public static SocksEntity testDoubleSocksEntity = SocksEntity.builder()
            .typeId(3L)
            .colour("yellow")
            .cotton(90)
            .amount(32)
            .build();

    public static SocksEntity testTripleSocksEntity = SocksEntity.builder()
            .typeId(3L)
            .colour("yellow")
            .cotton(90)
            .amount(48)
            .build();
}