package com.FoodOrderingApp.repository;

import com.FoodOrderingApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Kullanıcı adına göre kullanıcı bulma
    Optional<User> findByUsername(String username);

    // E-posta adresine göre kullanıcı bulma
    Optional<User> findByEmail(String email);

    // Belirli bir role sahip tüm kullanıcıları bulma
    List<User> findByRole(String role);

    // Belirli bir isimle başlayan kullanıcıları bulma
    List<User> findByUsernameStartingWith(String prefix);

    // Belirli bir isimle biten kullanıcıları bulma
    List<User> findByUsernameEndingWith(String suffix);

    // Kullanıcı adı ve e-posta adresine göre kullanıcı bulma
    Optional<User> findByUsernameAndEmail(String username, String email);

    // Kullanıcı adına göre kullanıcıları sıralı olarak bulma
    List<User> findByOrderByUsernameAsc();

    // E-posta adresine göre kullanıcıların sayısını bulma
    long countByEmail(String email);

    // Belirli bir role sahip kullanıcıların sayısını bulma
    long countByRole(String role);

    // E-posta adresine göre kullanıcı silme
    void deleteByEmail(String email);

    // Kullanıcı adına göre kullanıcı var olup olmadığını kontrol etme
    boolean existsByUsername(String username);
}
