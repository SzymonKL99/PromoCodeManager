package com.example.promocodemanager.service;

import com.example.promocodemanager.dto.PromoCodeDto;
import com.example.promocodemanager.exceptions.PromoCodeNotFoundException;
import com.example.promocodemanager.mapper.PromoCodeMapper;
import com.example.promocodemanager.model.PromoCode;
import com.example.promocodemanager.repository.PromoCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@RequiredArgsConstructor
@Service
public class PromoCodeServiceImpl implements PromoCodeService {

    private final PromoCodeRepository promoCodeRepository;
    private final PromoCodeMapper promoCodeMapper;

    @Override
    public PromoCodeDto findPromoCodeById(Long id) throws PromoCodeNotFoundException {
        PromoCode promoCode = promoCodeRepository.findById(id)
                .orElseThrow(() -> new PromoCodeNotFoundException("PromoCode with ID " + id + " not found"));
        return promoCodeMapper.mapToPromoCodeDto(promoCode);
    }

    @Override
    public PromoCodeDto createPromoCode(PromoCodeDto promoCodeDto) {
        PromoCode promoCode = promoCodeMapper.mapToPromoCode(promoCodeDto);
        PromoCode savedPromoCode = promoCodeRepository.save(promoCode);
        return promoCodeMapper.mapToPromoCodeDto(savedPromoCode);
    }

    @Override
    public List<PromoCodeDto> findAllPromoCodes() {
        return promoCodeRepository.findAll().stream()
                .map(promoCodeMapper::mapToPromoCodeDto)
                .collect(Collectors.toList());
    }


}
