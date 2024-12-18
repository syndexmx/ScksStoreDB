package com.github.syndexmx.socksbase.repositories.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "socks")
public class SocksEntity implements Serializable {

    @Id
            @Column(name = "type_id")
            @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long typeId;

    @Column(name = "colour")
    String colour;

    @Column(name = "cotton")
    Integer cotton;

    @Column(name = "amount")
    Integer amount;
}
