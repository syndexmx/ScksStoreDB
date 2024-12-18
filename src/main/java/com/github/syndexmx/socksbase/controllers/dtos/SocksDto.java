package com.github.syndexmx.socksbase.controllers.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SocksDto {

    Long typeId;
    String colour;
    Integer cotton;
    Integer amount;
}
