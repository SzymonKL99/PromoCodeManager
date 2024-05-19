package com.example.promocodemanager.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PromoCodeDto {

    private Long id;
    private String code;
    private LocalDate expirationDate;
    private BigDecimal discount;
    private String currency;
    private int maxUsages;
}
