package com.FoodOrderingApp.repository;

import com.FoodOrderingApp.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    // Sepete ait tüm öğeleri bulma
    List<CartItem> findByCartId(Long cartId);

    // İhtiyaca bağlı olarak burada diğer özel sorgular tanımlanabilir.
}
