package com.example.promocodemanager.service;

import com.example.promocodemanager.dto.PromoCodeDto;
import com.example.promocodemanager.exceptions.InvalidPromoCodeException;
import com.example.promocodemanager.exceptions.PromoCodeNotFoundException;
import com.example.promocodemanager.mapper.PromoCodeMapper;
import com.example.promocodemanager.model.PromoCode;
import com.example.promocodemanager.repository.PromoCodeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PromoCodeServiceImplTest {

    @Mock
    private PromoCodeRepository promoCodeRepository;

    @Mock
    private PromoCodeMapper promoCodeMapper;

    @InjectMocks
    private PromoCodeServiceImpl promoCodeService;

    private PromoCode promoCode;
    private PromoCodeDto promoCodeDto;

    @BeforeEach
    public void setUp() {
        promoCode = PromoCode.builder()
                .id(1L)
                .code("DISCOUNT2024")
                .expirationDate(LocalDate.of(2024, 10, 10))
                .discount(new BigDecimal("15.00"))
                .currency("USD")
                .maxUsages(100)
                .usageCount(10)
                .build();

        promoCodeDto = PromoCodeDto.builder()
                .id(1L)
                .code("DISCOUNT2024")
                .expirationDate(LocalDate.of(2024, 10, 10))
                .discount(new BigDecimal("15.00"))
                .currency("USD")
                .maxUsages(100)
                .usageCount(10)
                .build();
    }

    @Test
    public void testFindPromoCodeById_PromoCodeNotFound() {
        // Given
        when(promoCodeRepository.findById(1L)).thenReturn(Optional.empty());

        // When, Then
        assertThrows(PromoCodeNotFoundException.class, () -> promoCodeService.findPromoCodeById(1L));
    }

    @Test
    public void testCreatePromoCode_InvalidPromoCode() {
        // Given
        promoCodeDto.setCode("ab");

        // When, Then
        assertThrows(InvalidPromoCodeException.class, () -> promoCodeService.createPromoCode(promoCodeDto));
    }

    @Test
    public void testFindPromoCodeById_PromoCodeFound() {
        // Given
        when(promoCodeRepository.findById(1L)).thenReturn(Optional.of(promoCode));
        when(promoCodeMapper.mapToPromoCodeDto(promoCode)).thenReturn(promoCodeDto);

        // When
        PromoCodeDto result = promoCodeService.findPromoCodeById(1L);

        // Then
        assertEquals(promoCodeDto, result);
    }

}
