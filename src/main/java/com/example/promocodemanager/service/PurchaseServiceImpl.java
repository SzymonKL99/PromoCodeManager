package com.example.promocodemanager.service;

import com.example.promocodemanager.dto.ProductDto;
import com.example.promocodemanager.dto.PromoCodeDto;
import com.example.promocodemanager.dto.PurchaseDto;
import com.example.promocodemanager.exceptions.ProductNotFoundExceptions;
import com.example.promocodemanager.exceptions.PromoCodeNotFoundException;
import com.example.promocodemanager.mapper.PurchaseMapper;
import com.example.promocodemanager.model.Product;
import com.example.promocodemanager.model.PromoCode;
import com.example.promocodemanager.repository.ProductRepository;
import com.example.promocodemanager.repository.PromoCodeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import org.apache.logging.log4j.Logger;


@AllArgsConstructor
@Slf4j
@Service
public class PurchaseServiceImpl implements PurchaseService {


    private final PromoCodeRepository promoCodeRepository;
    private final ProductRepository productRepository;


    private static final Logger logger = LogManager.getLogger(PurchaseServiceImpl.class);


    public BigDecimal calculateDiscountedPrice(Long productId, Long promoCodeId) throws PromoCodeNotFoundException {
        Optional<PromoCode> promoCodeOpt = promoCodeRepository.findById(promoCodeId);
        if (promoCodeOpt.isEmpty()) {
            throw new PromoCodeNotFoundException("PromoCode with code " + promoCodeId + " not found");

        }

        PromoCode promoCode = promoCodeOpt.get();

        if (promoCode.getExpirationDate().isBefore(LocalDate.now())) {
            logger.warn("PromoCode has expired. Regular price will be applied.");
            return getProductRegularPrice(productId);
        }

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundExceptions("Product with ID " + productId + " not found"));

        if (!promoCode.getCurrency().equals(product.getCurrency())) {
            logger.warn("Currency mismatch between promo code and product. Regular price will be applied.");
            return getProductRegularPrice(productId);
        }

        if (promoCode.getUsageCount() >= promoCode.getMaxUsages()) {
            logger.warn("PromoCode has reached maximum usage limit. Regular price will be applied.");

            return getProductRegularPrice(productId);
        }

        BigDecimal regularPrice = product.getRegularPrice();
        BigDecimal discount = promoCode.getDiscount();
        BigDecimal discountedPrice = regularPrice.subtract(discount);

        if (discountedPrice.compareTo(BigDecimal.ZERO) < 0) {
            return BigDecimal.ZERO;
        }

        return discountedPrice;
    }

    private BigDecimal getProductRegularPrice(Long productId) throws ProductNotFoundExceptions {
        return getProductById(productId).getRegularPrice();
    }
    private Product getProductById(Long productId) throws ProductNotFoundExceptions {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundExceptions("Product with ID " + productId + " not found"));
    }

}
