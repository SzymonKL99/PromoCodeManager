package com.example.promocodemanager.service;

import com.example.promocodemanager.dto.ProductDto;
import com.example.promocodemanager.exceptions.ProductNotFoundExceptions;

import java.util.List;

public interface ProductService {

    ProductDto findProductById(Long id) throws ProductNotFoundExceptions;
    List<ProductDto> findAllProducts();
    ProductDto createProduct(ProductDto productDto);
    ProductDto updateProduct(ProductDto productDto, Long id) throws ProductNotFoundExceptions;


}
