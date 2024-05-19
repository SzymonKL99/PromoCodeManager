package com.example.promocodemanager.mapper;

import com.example.promocodemanager.dto.ProductDto;
import com.example.promocodemanager.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ProductMapperTest {

    private ProductDto productDto;
    private Product product;

    @Autowired
    private ProductMapper productMapper;

    @Test
    public void testMapToProduct() {
        // Given
        ProductDto productDto = ProductDto.builder()
                .id(1L)
                .name("Test Product")
                .description("Test Description")
                .regularPrice(BigDecimal.valueOf(99.99))
                .currency("USD")
                .build();

        // When
        Product product = productMapper.mapToProduct(productDto);

        // Then
        assertEquals(productDto.getId(), product.getId());
        assertEquals(productDto.getName(), product.getName());
        assertEquals(productDto.getDescription(), product.getDescription());
        assertEquals(productDto.getRegularPrice(), product.getRegularPrice());
        assertEquals(productDto.getCurrency(), product.getCurrency());
    }
}
