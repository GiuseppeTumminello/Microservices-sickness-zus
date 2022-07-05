package com.acoustic.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SicknessZus {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private BigDecimal sicknessZusAmount;



}
