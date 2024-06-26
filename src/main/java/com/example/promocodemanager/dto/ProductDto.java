package com.example.promocodemanager.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ProductDto {

    private Long id;
    private String name;
    private String description;
    private BigDecimal regularPrice;
    private String currency;


}
