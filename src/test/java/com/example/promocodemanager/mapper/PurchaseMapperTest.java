package com.example.promocodemanager.mapper;

import com.example.promocodemanager.dto.ProductDto;
import com.example.promocodemanager.dto.PromoCodeDto;
import com.example.promocodemanager.dto.PurchaseDto;
import com.example.promocodemanager.model.Product;
import com.example.promocodemanager.model.PromoCode;
import com.example.promocodemanager.model.Purchase;
import com.example.promocodemanager.service.ProductService;
import com.example.promocodemanager.service.PromoCodeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class PurchaseMapperTest {

    @InjectMocks
    private PurchaseMapper purchaseMapper;

    @Mock
    private ProductMapper productMapper;

    @Mock
    private ProductService productService;

    @Mock
    private PromoCodeMapper promoCodeMapper;

    @Mock
    private PromoCodeService promoCodeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testMapToPurchase() {
        // Given
        PurchaseDto purchaseDto = PurchaseDto.builder()
                .id(1L)
                .date(LocalDate.of(2023, 5, 19))
                .regularPrice(BigDecimal.valueOf(100))
                .discountedPrice(BigDecimal.valueOf(80))
                .currency("USD")
                .productId(1L)
                .promoCodeId(2L)
                .build();

        ProductDto productDto = ProductDto.builder()
                .id(1L)
                .build();

        PromoCodeDto promoCodeDto = PromoCodeDto.builder()
                .id(2L)
                .build();

        Product product = Product.builder()
                .id(1L)
                .build();

        PromoCode promoCode = PromoCode.builder()
                .id(2L)
                .build();

        when(productService.findProductById(anyLong())).thenReturn(productDto);
        when(promoCodeService.findPromoCodeById(anyLong())).thenReturn(promoCodeDto);
        when(productMapper.mapToProduct(productDto)).thenReturn(product);
        when(promoCodeMapper.mapToPromoCode(promoCodeDto)).thenReturn(promoCode);

        // When
        Purchase purchase = purchaseMapper.mapToPurchase(purchaseDto);

        // Then
        assertEquals(purchaseDto.getId(), purchase.getId());
        assertEquals(purchaseDto.getDate(), purchase.getDate());
        assertEquals(purchaseDto.getRegularPrice(), purchase.getRegularPrice());
        assertEquals(purchaseDto.getDiscountedPrice(), purchase.getDiscountedPrice());
        assertEquals(purchaseDto.getCurrency(), purchase.getCurrency());
        assertEquals(product, purchase.getProduct());
        assertEquals(promoCode, purchase.getPromoCode());
    }

    @Test
    public void testMapToPurchaseDto() {
        // Given
        Product product = Product.builder()
                .id(1L)
                .build();

        PromoCode promoCode = PromoCode.builder()
                .id(2L)
                .build();

        Purchase purchase = Purchase.builder()
                .id(1L)
                .date(LocalDate.of(2023, 5, 19))
                .regularPrice(BigDecimal.valueOf(100))
                .discountedPrice(BigDecimal.valueOf(80))
                .currency("USD")
                .product(product)
                .promoCode(promoCode)
                .build();

        // When
        PurchaseDto purchaseDto = purchaseMapper.mapToPurchaseDto(purchase);

        // Then
        assertEquals(purchase.getId(), purchaseDto.getId());
        assertEquals(purchase.getDate(), purchaseDto.getDate());
        assertEquals(purchase.getRegularPrice(), purchaseDto.getRegularPrice());
        assertEquals(purchase.getDiscountedPrice(), purchaseDto.getDiscountedPrice());
        assertEquals(purchase.getCurrency(), purchaseDto.getCurrency());
        assertEquals(purchase.getProduct().getId(), purchaseDto.getProductId());
        assertEquals(purchase.getPromoCode().getId(), purchaseDto.getPromoCodeId());
    }

}
