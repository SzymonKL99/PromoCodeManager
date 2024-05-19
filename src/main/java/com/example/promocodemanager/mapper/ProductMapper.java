package com.example.promocodemanager.mapper;

import com.example.promocodemanager.dto.ProductDto;
import com.example.promocodemanager.model.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProductMapper {

    public Product mapToProduct(ProductDto productDto) {
        return Product.builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .description(productDto.getDescription())
                .regularPrice(productDto.getRegularPrice())
                .currency(productDto.getCurrency())
                .build();

    }

    public ProductDto mapToProductDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .regularPrice(product.getRegularPrice())
                .currency(product.getCurrency())
                .build();
    }

}
