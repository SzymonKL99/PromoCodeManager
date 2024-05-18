package com.example.promocodemanager.repository;

import com.example.promocodemanager.model.PromoCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface PromoCodeRepository extends JpaRepository<PromoCode, Long> {
}
