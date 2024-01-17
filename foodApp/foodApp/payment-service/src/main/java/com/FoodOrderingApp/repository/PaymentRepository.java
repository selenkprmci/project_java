package com.FoodOrderingApp.repository;

import com.FoodOrderingApp.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    // Basic CRUD operations are provided by JpaRepository
}
