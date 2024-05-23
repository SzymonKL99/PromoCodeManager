package com.example.promocodemanager.controller;

import com.example.promocodemanager.dto.PurchaseDto;
import com.example.promocodemanager.dto.SalesReportDto;
import com.example.promocodemanager.exceptions.ProductNotFoundExceptions;
import com.example.promocodemanager.exceptions.PromoCodeNotFoundException;
import com.example.promocodemanager.service.PurchaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/purchase")
public class PurchaseController {

    private final PurchaseServiceImpl purchaseService;

    @GetMapping(value = "/discountedPrice")
    public ResponseEntity<BigDecimal> getDiscountedPrice(
            @RequestParam("productId") Long productId,
            @RequestParam("promoCodeId") Long promoCodeId) {

            BigDecimal discountedPrice = purchaseService.calculateDiscountedPrice(productId, promoCodeId);
            return ResponseEntity.ok(discountedPrice);
    }

    @PostMapping(value = "/simulate")
    public ResponseEntity<PurchaseDto> simulatePurchase(
            @RequestParam("productId") Long productId,
            @RequestParam("promoCodeId") Long promoCodeId) {

            PurchaseDto purchaseDto = purchaseService.simulatePurchase(productId, promoCodeId);
            return ResponseEntity.ok(purchaseDto);
    }

    @GetMapping(value = "/salesReport")
    public ResponseEntity<Map<String, SalesReportDto>> getSalesReport() {
        Map<String, SalesReportDto> report = purchaseService.generateSalesReport();
        return ResponseEntity.ok(report);
    }
}
