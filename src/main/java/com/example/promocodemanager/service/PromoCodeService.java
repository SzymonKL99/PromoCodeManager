package com.example.promocodemanager.service;

import com.example.promocodemanager.dto.PromoCodeDto;
import com.example.promocodemanager.exceptions.PromoCodeNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PromoCodeService {

    PromoCodeDto findPromoCodeById(Long id) throws PromoCodeNotFoundException;
    PromoCodeDto createPromoCode(PromoCodeDto promoCodeDto);
    List<PromoCodeDto> findAllPromoCodes();

}
