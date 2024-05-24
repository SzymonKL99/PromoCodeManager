package com.example.promocodemanager.service;

import com.example.promocodemanager.dto.PurchaseDto;
import com.example.promocodemanager.dto.SalesReportDto;
import com.example.promocodemanager.exceptions.ProductNotFoundExceptions;
import com.example.promocodemanager.exceptions.PromoCodeNotFoundException;

import java.math.BigDecimal;
import java.util.Map;

public interface PurchaseService {

    BigDecimal calculateDiscountedPrice(Long productId, Long promoCodeId) throws PromoCodeNotFoundException;

    PurchaseDto simulatePurchase(Long productId, Long promoCodeId) throws PromoCodeNotFoundException, ProductNotFoundExceptions;

    Map<String, SalesReportDto> generateSalesReport();

}
