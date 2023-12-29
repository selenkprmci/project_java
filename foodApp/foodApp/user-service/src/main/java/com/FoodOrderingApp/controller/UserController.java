package com.FoodOrderingApp.controller;

import com.FoodOrderingApp.model.User;
import com.FoodOrderingApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import java.util.List;
import java.util.Optional;

@Controller
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
    public String registerUser(User user) {
        userService.createUser(user);
        return "redirect:/api/users"; // Başarılı kayıttan sonra kullanıcıları listeleme sayfasına yönlendir
    }
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    // Kullanıcı bilgilerini ID'ye göre getirme endpoint'i
    @GetMapping("/profile/{userId}")
    public ResponseEntity<Optional<User>> getUserProfile(@PathVariable Long userId) {
        Optional<User> user = userService.getUserById(userId);
        return ResponseEntity.ok(user); // Kullanıcı profilini döndür
    }

    // Tüm kullanıcıları getirme endpoint'i
    @GetMapping
    public String getAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users"; // templates dizinindeki 'users.html' şablonunu kullanır
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
