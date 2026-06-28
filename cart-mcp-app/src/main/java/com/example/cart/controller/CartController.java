package com.example.cart.controller;

import com.example.cart.dto.*;
import com.example.cart.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/items")
    public List<CartItemResponse> listItems() {
        return cartService.findAllItems();
    }

    @GetMapping("/items/{id}")
    public CartItemResponse getItem(@PathVariable Long id) {
        return cartService.findItemById(id);
    }

    @GetMapping("/summary")
    public CartSummaryResponse summary() {
        return cartService.getSummary();
    }

    @PostMapping("/items")
    @ResponseStatus(HttpStatus.CREATED)
    public CartItemResponse addItem(@Valid @RequestBody AddToCartRequest request) {
        return cartService.addItem(request);
    }

    @PutMapping("/items/{id}")
    public CartItemResponse updateItem(@PathVariable Long id, @Valid @RequestBody UpdateCartItemRequest request) {
        return cartService.updateItem(id, request);
    }

    @DeleteMapping("/items/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeItem(@PathVariable Long id) {
        cartService.removeItem(id);
    }

    @DeleteMapping("/items")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void clearCart() {
        cartService.clearCart();
    }
}
