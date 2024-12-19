package com.github.syndexmx.socksbase.model;

public class TestSocks {

    public static Socks testSocks = Socks.builder()
            .typeId(3L)
            .colour("yellow")
            .cotton(90)
            .amount(16)
            .build();

    public static Socks testDoubleSocks = Socks.builder()
            .typeId(3L)
            .colour("yellow")
            .cotton(90)
            .amount(32)
            .build();

    public static Socks testTripleSocks = Socks.builder()
            .typeId(3L)
            .colour("yellow")
            .cotton(90)
            .amount(48)
            .build();

    public static Socks testOtherSocks = Socks.builder()
            .typeId(4L)
            .colour("magenta")
            .cotton(88)
            .amount(4)
            .build();

}
