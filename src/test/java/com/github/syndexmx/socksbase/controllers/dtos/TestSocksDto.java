package com.github.syndexmx.socksbase.controllers.dtos;

public class TestSocksDto {

    public static SocksDto testSocksDto = SocksDto.builder()
            .typeId(3L)
            .colour("yellow")
            .cotton(90)
            .amount(16)
            .build();

    public static SocksDto testDoubleSocksDto = SocksDto.builder()
            .typeId(3L)
            .colour("yellow")
            .cotton(90)
            .amount(32)
            .build();

    public static SocksDto testTripleSocksDto = SocksDto.builder()
            .typeId(3L)
            .colour("yellow")
            .cotton(90)
            .amount(48)
            .build();
}
