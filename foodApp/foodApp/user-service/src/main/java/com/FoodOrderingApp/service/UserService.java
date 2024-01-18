package com.FoodOrderingApp.service;

import com.FoodOrderingApp.model.User;
import com.FoodOrderingApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    // UserRepository enjekte edilir
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Yeni kullanıcı oluşturur
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // ID'ye göre kullanıcı getirir
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Tüm kullanıcıları listeler
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Kullanıcıyı günceller
    public User updateUser(Long id, User userDetails) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(userDetails.getUsername());
                    user.setEmail(userDetails.getEmail());
                    // Diğer alanları güncelle
                    return userRepository.save(user);
                })
                .orElseGet(() -> {
                    userDetails.setId(id);
                    return userRepository.save(userDetails);
                });
    }

    // Kullanıcıyı siler
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    public boolean authenticate(String username, String password) {
        // UserRepository'den Optional<User> türünde bir değer alınır
        Optional<User> userOptional = userRepository.findByUsername(username);

        // Optional değer içindeki User nesnesini kontrol edin
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // Şifre doğrulaması yapın (burada daha güvenli bir yöntem kullanılmalıdır)
            if (user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

}