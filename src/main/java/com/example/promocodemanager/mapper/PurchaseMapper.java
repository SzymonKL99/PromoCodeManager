package com.example.promocodemanager.mapper;

import com.example.promocodemanager.dto.ProductDto;
import com.example.promocodemanager.dto.PromoCodeDto;
import com.example.promocodemanager.dto.PurchaseDto;
import com.example.promocodemanager.model.Product;
import com.example.promocodemanager.model.PromoCode;
import com.example.promocodemanager.model.Purchase;
import com.example.promocodemanager.service.ProductService;
import com.example.promocodemanager.service.PromoCodeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PurchaseMapper {

    private final ProductMapper productMapper;
    private final ProductService productService;
    private final PromoCodeMapper promoCodeMapper;
    private final PromoCodeService promoCodeService;

    public Purchase mapToPurchase(PurchaseDto purchaseDto) {
        return Purchase.builder()
                .id(purchaseDto.getId())
                .date(purchaseDto.getDate())
                .regularPrice(purchaseDto.getRegularPrice())
                .discountedPrice(purchaseDto.getDiscountedPrice())
                .currency(purchaseDto.getCurrency())
                .product(getProductByPurchase(purchaseDto))
                .promoCode(getPromoCodeByPurchase(purchaseDto))
                .build();
    }

    public PurchaseDto mapToPurchaseDto(Purchase purchase) {
        return PurchaseDto.builder()
                .id(purchase.getId())
                .date(purchase.getDate())
                .regularPrice(purchase.getRegularPrice())
                .discountedPrice(purchase.getDiscountedPrice())
                .currency(purchase.getCurrency())
                .productId(purchase.getProduct().getId())
                .promoCodeId(purchase.getPromoCode().getId())
                .build();
    }

    public Product getProductByPurchase(PurchaseDto purchaseDto) {
        Long productId = purchaseDto.getProductId();
        ProductDto productDto = productService.findProductById(productId);
        return productMapper.mapToProduct(productDto);
    }

    public PromoCode getPromoCodeByPurchase(PurchaseDto purchaseDto) {
        Long promoCodeId = purchaseDto.getPromoCodeId();
        PromoCodeDto promoCodeDto = promoCodeService.findPromoCodeById(promoCodeId);
        return promoCodeMapper.mapToPromoCode(promoCodeDto);
    }



}
