package com.example.promocodemanager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PROMO_CODE")
public class PromoCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROMO_CODE_ID")
    private Long id;

    @Column(name = "CODE", unique = true)
    private String code;

    @Column(name = "EXPIRATION_DATE")
    private LocalDate expirationDate;

    @Column(name = "DISCOUNT")
    private BigDecimal discount;

    @Column(name = "CURRENCY")
    private String currency;

    @Column(name = "MAX_USAGES")
    private int maxUsages;

    @Column(name = "USAGE_COUNT")
    private int usageCount;

    @ManyToMany
    @JoinTable(
            name = "product_promo_code",
            joinColumns = @JoinColumn(name = "promo_code_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Product> products;

    @OneToMany(mappedBy = "promoCode")
    private Set<Purchase> purchases;
}
