package com.github.syndexmx.socksbase.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Socks {

    Long typeId;
    String colour;
    Integer cotton;
    Integer amount;

    public Socks add(Integer addedAmount){
        return Socks.builder()
                .typeId(this.typeId)
                .colour(this.colour)
                .cotton(this.cotton)
                .amount(this.amount + addedAmount)
                .build();
    }

    public Socks remove(Integer subtractedAmount){
        if (this.amount < subtractedAmount) {
            throw new RuntimeException("Not enough socks");
        }
        return Socks.builder()
                .typeId(this.typeId)
                .colour(this.colour)
                .cotton(this.cotton)
                .amount(this.amount - subtractedAmount)
                .build();
    }

}
