package com.example.promocodemanager.service;

import com.example.promocodemanager.dto.ProductDto;

public interface ProductService {

    ProductDto findProductById(Long id);
}
