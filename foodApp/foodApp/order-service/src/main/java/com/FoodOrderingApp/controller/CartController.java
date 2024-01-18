package com.FoodOrderingApp.controller;

import com.FoodOrderingApp.model.Cart;
import com.FoodOrderingApp.model.CartItem;
import com.FoodOrderingApp.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    // Sepete ürün ekleme
    @PostMapping("/{userId}")
    public ResponseEntity<Cart> addItemToCart(@PathVariable Long userId, @RequestBody CartItem cartItem) {
        Cart updatedCart = cartService.addItemToCart(userId, cartItem);
        if (updatedCart == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedCart);
    }
    @PostMapping("/create/{userId}")
    public ResponseEntity<Cart> createCartForUser(@PathVariable Long userId) {
        Cart newCart = new Cart(userId);
        // Cart nesnesini kaydetme işlemi (cartService üzerinden)
        Cart savedCart = cartService.saveCart(newCart);
        return ResponseEntity.ok(savedCart);
    }
    // Kullanıcının sepetini görüntüleme
    @GetMapping("/{userId}")
    public String getCartByUserId(@PathVariable Long userId, Model model) {
        Cart cart = cartService.getCartByUserId(userId);
        if (cart == null) {
            // Sepet bulunamazsa, örneğin bir hata sayfasına yönlendirilebilir
            return "cartNotFound";
        }
        model.addAttribute("cart", cart);
        return "cart"; // cart.html sayfasını göster
    }

    // Sepetten ürün çıkarma
    @DeleteMapping("/{userId}/{itemId}")
    public ResponseEntity<Cart> removeItemFromCart(@PathVariable Long userId, @PathVariable Long itemId) {
        Cart updatedCart = cartService.removeItemFromCart(userId, itemId);
        if (updatedCart == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedCart);
    }

    // Sepeti temizleme
    @DeleteMapping("/{userId}")
    public ResponseEntity<java.lang.Object> clearCart(@PathVariable Long userId) {
        boolean isCleared = cartService.clearCart(userId);
        if (!isCleared) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}


