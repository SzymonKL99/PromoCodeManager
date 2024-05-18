package com.example.promocodemanager.dto;

import com.example.promocodemanager.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PurchaseDto {

    private Long id;
    private LocalDate date;
    private BigDecimal regularPrice;
    private BigDecimal discountedPrice;
    private String currency;
    private Long productId;
    private Long promoCodeId;
}
