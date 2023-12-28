package com.FoodOrderingApp.controller;

import com.FoodOrderingApp.model.User;
import com.FoodOrderingApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    // UserService enjekte edilir
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Yeni kullanıcı oluşturma endpoint'i
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User newUser) {
        User user = userService.createUser(newUser);
        return ResponseEntity.ok(user);
    }

    // Kullanıcı bilgilerini ID'ye göre getirme endpoint'i
    @GetMapping("/{userId}")
    public ResponseEntity<Optional<User>> getUserProfile(@PathVariable Long userId) {
        Optional<User> user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    // Tüm kullanıcıları getirme endpoint'i
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // Kullanıcı güncelleme endpoint'i
    // Burada daha fazla detay eklenebilir (örneğin, güncellenecek alanlar)
    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User userDetails) {
        User updatedUser = userService.updateUser(userId, userDetails);
        return ResponseEntity.ok(updatedUser);
    }

    // Kullanıcı silme endpoint'i
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }
}
