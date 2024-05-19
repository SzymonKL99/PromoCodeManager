package com.example.promocodemanager.service;

import com.example.promocodemanager.dto.PromoCodeDto;
import org.springframework.stereotype.Service;

@Service
public interface PromoCodeService {

    PromoCodeDto findPromoCodeById(Long id);
}
