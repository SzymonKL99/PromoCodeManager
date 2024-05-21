package com.example.promocodemanager.mapper;

import com.example.promocodemanager.dto.PromoCodeDto;
import com.example.promocodemanager.model.PromoCode;
import org.springframework.stereotype.Component;

@Component
public class PromoCodeMapper {

    public PromoCode mapToPromoCode(PromoCodeDto promoCodeDto) {
        return PromoCode.builder()
                .id(promoCodeDto.getId())
                .code(promoCodeDto.getCode())
                .expirationDate(promoCodeDto.getExpirationDate())
                .discount(promoCodeDto.getDiscount())
                .currency(promoCodeDto.getCurrency())
                .maxUsages(promoCodeDto.getMaxUsages())
                .usageCount(promoCodeDto.getUsageCount())
                .build();
    }

    public PromoCodeDto mapToPromoCodeDto(PromoCode promoCode) {
        return PromoCodeDto.builder()
                .id(promoCode.getId())
                .code(promoCode.getCode())
                .expirationDate(promoCode.getExpirationDate())
                .discount(promoCode.getDiscount())
                .currency(promoCode.getCurrency())
                .maxUsages(promoCode.getMaxUsages())
                .usageCount(promoCode.getUsageCount())
                .build();
    }
}
