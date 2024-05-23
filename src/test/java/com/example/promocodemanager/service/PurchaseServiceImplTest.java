package com.example.promocodemanager.service;

import com.example.promocodemanager.dto.SalesReportDto;
import com.example.promocodemanager.exceptions.ProductNotFoundExceptions;
import com.example.promocodemanager.exceptions.PromoCodeNotFoundException;
import com.example.promocodemanager.model.Product;
import com.example.promocodemanager.model.PromoCode;
import com.example.promocodemanager.model.Purchase;
import com.example.promocodemanager.repository.ProductRepository;
import com.example.promocodemanager.repository.PromoCodeRepository;
import com.example.promocodemanager.repository.PurchaseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PurchaseServiceImplTest {

    @Mock
    private PromoCodeRepository promoCodeRepository;

    @Mock
    private PurchaseRepository purchaseRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private PurchaseServiceImpl purchaseService;

    private Product product;
    private PromoCode promoCode;

    private Purchase purchase;
    private Purchase purchase2;
    private Purchase purchase3;
    private List<Purchase> purchases;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setId(1L);
        product.setRegularPrice(new BigDecimal("100.00"));
        product.setCurrency("USD");

        promoCode = new PromoCode();
        promoCode.setId(1L);
        promoCode.setDiscount(new BigDecimal("20.00"));
        promoCode.setCurrency("USD");
        promoCode.setExpirationDate(LocalDate.now().plusDays(1));
        promoCode.setMaxUsages(10);
        promoCode.setUsageCount(5);

        purchase = new Purchase();
        purchase.setRegularPrice(new BigDecimal("100.00"));
        purchase.setDiscountedPrice(new BigDecimal("80.00"));
        purchase.setCurrency("USD");

        purchase2 = new Purchase();
        purchase2.setRegularPrice(new BigDecimal("200.00"));
        purchase2.setDiscountedPrice(new BigDecimal("180.00"));
        purchase2.setCurrency("USD");

        purchase3 = new Purchase();
        purchase3.setRegularPrice(new BigDecimal("300.00"));
        purchase3.setDiscountedPrice(new BigDecimal("250.00"));
        purchase3.setCurrency("EUR");

        purchases = Arrays.asList(purchase, purchase2, purchase3);
    }

    @Test
    void testCalculateDiscountedPrice_PromoCodeNotFound() {
        //Given
        when(promoCodeRepository.findById(1L)).thenReturn(Optional.empty());

        //When, Then
        assertThrows(PromoCodeNotFoundException.class, () -> {
            purchaseService.calculateDiscountedPrice(1L, 1L);
        });
    }

    @Test
    void testCalculateDiscountedPrice_PromoCodeExpired() {
        //Given
        promoCode.setExpirationDate(LocalDate.now().minusDays(1));
        when(promoCodeRepository.findById(1L)).thenReturn(Optional.of(promoCode));
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        //When
        BigDecimal result = purchaseService.calculateDiscountedPrice(1L, 1L);

        //Then
        assertEquals(product.getRegularPrice(), result);
    }

    @Test
    void testCalculateDiscountedPrice_CurrencyMismatch() {
        //Given
        promoCode.setCurrency("EUR");
        when(promoCodeRepository.findById(1L)).thenReturn(Optional.of(promoCode));
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        //When
        BigDecimal result = purchaseService.calculateDiscountedPrice(1L, 1L);

        //Then
        assertEquals(product.getRegularPrice(), result);
    }

    @Test
    void testCalculateDiscountedPrice_MaxUsagesReached() {
        //Given
        promoCode.setUsageCount(10);
        when(promoCodeRepository.findById(1L)).thenReturn(Optional.of(promoCode));
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        //When
        BigDecimal result = purchaseService.calculateDiscountedPrice(1L, 1L);

        //Then
        assertEquals(product.getRegularPrice(), result);
    }

    @Test
    void testCalculateDiscountedPrice_DiscountApplied() {
        //Given
        when(promoCodeRepository.findById(1L)).thenReturn(Optional.of(promoCode));
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        //When
        BigDecimal result = purchaseService.calculateDiscountedPrice(1L, 1L);

        //Then
        assertEquals(new BigDecimal("80.00"), result);

    }

    @Test
    void testCalculateDiscountedPrice_DiscountExceedsPrice() {
        //Given
        promoCode.setDiscount(new BigDecimal("120.00"));
        when(promoCodeRepository.findById(1L)).thenReturn(Optional.of(promoCode));
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        //When
        BigDecimal result = purchaseService.calculateDiscountedPrice(1L, 1L);

        //Then
        assertEquals(BigDecimal.ZERO, result);
    }

    @Test
    void testCalculateDiscountedPrice_ProductNotFound() {
        //Given
        when(promoCodeRepository.findById(1L)).thenReturn(Optional.of(promoCode));
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        //When, Then
        assertThrows(ProductNotFoundExceptions.class, () -> {
            purchaseService.calculateDiscountedPrice(1L, 1L);
        });
    }

    @Test
    void testGenerateSalesReportForUSD() {
        // Given
        when(purchaseRepository.findAll()).thenReturn(purchases);

        // When
        Map<String, SalesReportDto> report = purchaseService.generateSalesReport();

        //Then
        SalesReportDto usReport = report.get("USD");
        assertEquals(new BigDecimal("300.00"), usReport.getTotalAmount());
        assertEquals(new BigDecimal("40.00"), usReport.getTotalDiscount());
        assertEquals(2, usReport.getNoOfPurchases());
    }

    @Test
    void testGenerateSalesReportForEUR() {
        // Given
        when(purchaseRepository.findAll()).thenReturn(purchases);

        // When
        Map<String, SalesReportDto> report = purchaseService.generateSalesReport();

        // Then
        SalesReportDto eurReport = report.get("EUR");
        assertEquals(new BigDecimal("300.00"), eurReport.getTotalAmount());
        assertEquals(new BigDecimal("50.00"), eurReport.getTotalDiscount());
        assertEquals(1, eurReport.getNoOfPurchases());


    }
}