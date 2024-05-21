package com.example.promocodemanager.controller;

import com.example.promocodemanager.dto.PromoCodeDto;
import com.example.promocodemanager.service.PromoCodeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/promoCode")
public class PromoCodeController {

    private final PromoCodeServiceImpl promoCodeService;

    @GetMapping
    public ResponseEntity<List<PromoCodeDto>> getAllPromoCodes() {
        List<PromoCodeDto> promoCodes = promoCodeService.findAllPromoCodes();
        return new ResponseEntity<>(promoCodes, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PromoCodeDto> getPromoCodeById(@PathVariable Long id) {
        PromoCodeDto promoCodeDto = promoCodeService.findPromoCodeById(id);
        return new ResponseEntity<>(promoCodeDto, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<PromoCodeDto> createPromoCode(@RequestBody PromoCodeDto promoCodeDto) {
        PromoCodeDto createdPromoCode = promoCodeService.createPromoCode(promoCodeDto);
        return new ResponseEntity<>(createdPromoCode, HttpStatus.CREATED);
    }

}
