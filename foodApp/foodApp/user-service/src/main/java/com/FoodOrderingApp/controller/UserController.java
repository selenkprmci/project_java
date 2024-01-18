package com.FoodOrderingApp.controller;

import com.FoodOrderingApp.model.User;
import com.FoodOrderingApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public String loginPage() {
        return "login"; // login.html sayfasını döndür
    }

    @PostMapping("/login")
    public ModelAndView login(@RequestParam("username") String username,
                              @RequestParam("password") String password) {
        boolean loginSuccess = userService.authenticate(username, password);

        if (loginSuccess) {
            // Giriş başarılıysa, ana sayfaya veya başka bir sayfaya yönlendir
            return new ModelAndView("redirect:http://localhost:8083/api/restaurants/home");

        } else {
            // Giriş başarısızsa, login sayfasına geri dön ve hata mesajı göster
            ModelAndView modelAndView = new ModelAndView("login");
            modelAndView.addObject("error", "Invalid username or password.");
            return modelAndView;
        }
    }
    // UserService enjekte edilir
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Yeni kullanıcı oluşturma endpoint'i

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
        userService.createUser(user);
        return "redirect:/api/users/login"; // Kayıttan sonra giriş sayfasına yönlendir
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register"; // register.html sayfasını döndür
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
    @PostMapping("/update/{userId}")
    public ModelAndView updateUser(@PathVariable Long userId, @ModelAttribute User userDetails) {
        User updatedUser = userService.updateUser(userId, userDetails);
        ModelAndView modelAndView = new ModelAndView("redirect:/api/users/profile/" + userId);
        return modelAndView; // Güncelleme sonrası kullanıcının profil sayfasına yönlendir
    }
    @GetMapping("/update")
    public String showUpdateForm(@PathVariable Long userId, Model model) {
        Optional<User> user = userService.getUserById(userId);
        if(user.isPresent()) {
            model.addAttribute("user", user.get());
            return "update"; // update.html sayfasını döndür
        } else {

            return "redirect:/api/users/login";
        }
    }

    // Kullanıcı silme endpoint'i
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }
}