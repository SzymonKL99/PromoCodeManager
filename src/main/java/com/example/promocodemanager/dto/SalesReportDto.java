package com.example.promocodemanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SalesReportDto {

    private String currency;
    private BigDecimal totalAmount;
    private BigDecimal totalDiscount;
    private int noOfPurchases;
}
