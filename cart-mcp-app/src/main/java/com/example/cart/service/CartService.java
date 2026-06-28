package com.example.cart.service;

import com.example.cart.dto.*;
import com.example.cart.exception.BadRequestException;
import com.example.cart.exception.ResourceNotFoundException;
import com.example.cart.model.CartItem;
import com.example.cart.model.Product;
import com.example.cart.repository.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartItemRepository cartItemRepository;
    private final ProductService productService;

    public List<CartItemResponse> findAllItems() {
        return cartItemRepository.findAll().stream()
                .map(CartItemResponse::from)
                .toList();
    }

    public CartItemResponse findItemById(Long id) {
        return CartItemResponse.from(getCartItem(id));
    }

    public CartSummaryResponse getSummary() {
        List<CartItemResponse> items = findAllItems();
        int totalQuantity = items.stream().mapToInt(CartItemResponse::getQuantity).sum();
        BigDecimal totalAmount = items.stream()
                .map(CartItemResponse::getLineTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return CartSummaryResponse.builder()
                .itemCount(items.size())
                .totalQuantity(totalQuantity)
                .totalAmount(totalAmount)
                .items(items)
                .build();
    }

    @Transactional
    public CartItemResponse addItem(AddToCartRequest request) {
        Product product = productService.getProduct(request.getProductId());

        if (product.getStock() < request.getQuantity()) {
            throw new BadRequestException("Not enough stock. Available: " + product.getStock());
        }

        CartItem item = cartItemRepository.findByProduct(product).orElse(null);
        if (item != null) {
            int newQuantity = item.getQuantity() + request.getQuantity();
            if (product.getStock() < newQuantity) {
                throw new BadRequestException("Not enough stock. Available: " + product.getStock());
            }
            item.setQuantity(newQuantity);
            item.setUnitPrice(product.getPrice());
            return CartItemResponse.from(cartItemRepository.save(item));
        }

        CartItem newItem = CartItem.builder()
                .product(product)
                .quantity(request.getQuantity())
                .unitPrice(product.getPrice())
                .build();
        return CartItemResponse.from(cartItemRepository.save(newItem));
    }

    @Transactional
    public CartItemResponse updateItem(Long id, UpdateCartItemRequest request) {
        CartItem item = getCartItem(id);
        Product product = item.getProduct();

        if (product.getStock() < request.getQuantity()) {
            throw new BadRequestException("Not enough stock. Available: " + product.getStock());
        }

        item.setQuantity(request.getQuantity());
        item.setUnitPrice(product.getPrice());
        return CartItemResponse.from(cartItemRepository.save(item));
    }

    @Transactional
    public void removeItem(Long id) {
        if (!cartItemRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cart item not found: " + id);
        }
        cartItemRepository.deleteById(id);
    }

    @Transactional
    public void clearCart() {
        cartItemRepository.deleteAll();
    }

    private CartItem getCartItem(Long id) {
        return cartItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cart item not found: " + id));
    }
}
