package com.example.promocodemanager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PRODUCT")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID")
    private Long id;

    @Column(name = "PRODUCT_NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "REGULAR_PRICE")
    private BigDecimal regularPrice;

    @Column(name = "CURRENCY")
    private String currency;

    @ManyToMany(mappedBy = "products")
    private Set<PromoCode> promoCodes;

    @OneToOne(mappedBy = "product")
    private Purchase purchase;

}
