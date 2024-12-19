package com.github.syndexmx.socksbase.controllers.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Schema(description = "Запись хранения (цвет - процентный состав)")
public class SocksDto {

    @Schema(description = "Идентификатор типа")
    Long typeId;

    @Schema(description = "Цвет")
    String colour;

    @Schema(description = "Процентный состав хлопка")
    Integer cotton;

    @Schema(description = "Количество")
    Integer amount;
}
