package com.FoodOrderingApp.service;


import com.FoodOrderingApp.model.Cart;
import com.FoodOrderingApp.model.CartItem;
import com.FoodOrderingApp.repository.CartItemRepository;
import com.FoodOrderingApp.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Autowired
    public CartService(CartRepository cartRepository, CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }

    // Sepete ürün ekleme
    public Cart addItemToCart(Long userId, CartItem cartItem) {
        Optional<Cart> cartOptional = cartRepository.findByUserId(userId);
        Cart cart = cartOptional.orElseGet(() -> new Cart(userId));

        cartItem.setCart(cart);
        cart.getCartItems().add(cartItem);

        cartItemRepository.save(cartItem);
        return cartRepository.save(cart);
    }

    // Kullanıcının sepetini görüntüleme
    public Cart getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId).orElse(null);
    }

    // Sepetten öğe çıkarma
    public Cart removeItemFromCart(Long userId, Long itemId) {
        Optional<Cart> cartOptional = cartRepository.findByUserId(userId);
        if (cartOptional.isPresent()) {
            Cart cart = cartOptional.get();
            cart.getCartItems().removeIf(item -> item.getId().equals(itemId));
            cartItemRepository.deleteById(itemId);
            return cartRepository.save(cart);
        }
        return null;
    }

    // Sepeti temizleme
    public boolean clearCart(Long userId) {
        Optional<Cart> cartOptional = cartRepository.findByUserId(userId);
        if (cartOptional.isPresent()) {
            Cart cart = cartOptional.get();
            cart.getCartItems().clear();
            cartItemRepository.deleteAll(cart.getCartItems());
            cartRepository.save(cart);
            return true;
        }
        return false;
    }

    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }
}

