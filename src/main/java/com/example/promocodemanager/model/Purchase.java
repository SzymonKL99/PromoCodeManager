package com.example.promocodemanager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PURCHASE")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DATE")
    private LocalDate date;

    @Column(name = "REGULAR_PRICE")
    private BigDecimal regularPrice;

    @Column(name = "DISCOUNTED_PRICE")
    private BigDecimal discountedPrice;

    @Column(name = "CURRENCY")
    private String currency;

    @OneToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "PROMO_CODE_ID")
    private PromoCode promoCode;


}
