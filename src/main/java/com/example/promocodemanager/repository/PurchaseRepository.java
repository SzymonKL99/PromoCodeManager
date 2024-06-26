package com.example.promocodemanager.repository;

import com.example.promocodemanager.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
}
