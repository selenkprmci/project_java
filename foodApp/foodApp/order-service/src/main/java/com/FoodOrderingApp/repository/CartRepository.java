package com.FoodOrderingApp.repository;


import com.FoodOrderingApp.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    // Kullanıcı ID'sine göre sepeti bulma
    Optional<Cart> findByUserId(Long userId);

    // İhtiyaca bağlı olarak burada diğer özel sorgular tanımlanabilir.
}
