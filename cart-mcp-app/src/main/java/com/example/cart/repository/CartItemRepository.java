package com.example.cart.repository;

import com.example.cart.model.CartItem;
import com.example.cart.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    Optional<CartItem> findByProduct(Product product);
}
