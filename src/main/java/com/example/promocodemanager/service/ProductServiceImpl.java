package com.example.promocodemanager.service;

import com.example.promocodemanager.dto.ProductDto;
import com.example.promocodemanager.exceptions.ProductNotFoundExceptions;
import com.example.promocodemanager.mapper.ProductMapper;
import com.example.promocodemanager.model.Product;
import com.example.promocodemanager.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    @Override
    public ProductDto findProductById(Long id) throws ProductNotFoundExceptions {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundExceptions("Product with ID " + id + " not found"));
        return productMapper.mapToProductDto(product);
    }

    @Override
    public List<ProductDto> findAllProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::mapToProductDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = productMapper.mapToProduct(productDto);
        Product savedProduct = productRepository.save(product);
        return productMapper.mapToProductDto(savedProduct);
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto, Long id) throws ProductNotFoundExceptions {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundExceptions("Product with ID " + id + " not found"));

        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setRegularPrice(productDto.getRegularPrice());
        product.setCurrency(productDto.getCurrency());

        Product updatedProduct = productRepository.save(product);
        return productMapper.mapToProductDto(updatedProduct);
    }

}
