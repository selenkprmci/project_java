package com.FoodOrderingApp.repository;



import com.FoodOrderingApp.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // Burada, JpaRepository'den gelen standart CRUD işlevleri ve sorgular mevcut.

    // İhtiyaca bağlı olarak özel sorgular burada tanımlanabilir.
    // Örnek: Kullanıcı ID'sine göre siparişleri bulma
    // List<Order> findByUserId(Long userId);
}

