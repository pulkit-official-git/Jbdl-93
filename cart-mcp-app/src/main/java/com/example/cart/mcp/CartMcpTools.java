package com.example.cart.mcp;

import com.example.cart.dto.AddToCartRequest;
import com.example.cart.dto.ProductRequest;
import com.example.cart.dto.UpdateCartItemRequest;
import com.example.cart.service.CartService;
import com.example.cart.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springaicommunity.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class CartMcpTools {

    private final ProductService productService;
    private final CartService cartService;
    private final ObjectMapper objectMapper;

    private String toJson(Object value) {
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(value);
        } catch (Exception e) {
            return "{\"error\":\"" + e.getMessage() + "\"}";
        }
    }

    @McpTool(name = "cart_list_products", description = "List all products in the catalog")
    public String listProducts() {
        return toJson(productService.findAll());
    }

    @McpTool(name = "cart_get_product", description = "Get a product by ID")
    public String getProduct(
            @McpToolParam(description = "Product ID", required = true) long id) {
        return toJson(productService.findById(id));
    }

    @McpTool(name = "cart_create_product", description = "Create a new product")
    public String createProduct(
            @McpToolParam(description = "Product name", required = true) String name,
            @McpToolParam(description = "Product description") String description,
            @McpToolParam(description = "Unit price", required = true) double price,
            @McpToolParam(description = "Stock quantity", required = true) int stock) {
        ProductRequest request = ProductRequest.builder()
                .name(name)
                .description(description)
                .price(BigDecimal.valueOf(price))
                .stock(stock)
                .build();
        return toJson(productService.create(request));
    }

    @McpTool(name = "cart_update_product", description = "Update an existing product")
    public String updateProduct(
            @McpToolParam(description = "Product ID", required = true) long id,
            @McpToolParam(description = "Product name", required = true) String name,
            @McpToolParam(description = "Product description") String description,
            @McpToolParam(description = "Unit price", required = true) double price,
            @McpToolParam(description = "Stock quantity", required = true) int stock) {
        ProductRequest request = ProductRequest.builder()
                .name(name)
                .description(description)
                .price(BigDecimal.valueOf(price))
                .stock(stock)
                .build();
        return toJson(productService.update(id, request));
    }

    @McpTool(name = "cart_delete_product", description = "Delete a product by ID")
    public String deleteProduct(
            @McpToolParam(description = "Product ID", required = true) long id) {
        productService.delete(id);
        return toJson("Product " + id + " deleted");
    }

    @McpTool(name = "cart_list_items", description = "List all items in the shopping cart")
    public String listCartItems() {
        return toJson(cartService.findAllItems());
    }

    @McpTool(name = "cart_get_summary", description = "Get cart summary with totals")
    public String getCartSummary() {
        return toJson(cartService.getSummary());
    }

    @McpTool(name = "cart_add_item", description = "Add a product to the cart")
    public String addToCart(
            @McpToolParam(description = "Product ID", required = true) long productId,
            @McpToolParam(description = "Quantity", required = true) int quantity) {
        AddToCartRequest request = AddToCartRequest.builder()
                .productId(productId)
                .quantity(quantity)
                .build();
        return toJson(cartService.addItem(request));
    }

    @McpTool(name = "cart_update_item", description = "Update quantity of a cart line item")
    public String updateCartItem(
            @McpToolParam(description = "Cart item ID", required = true) long id,
            @McpToolParam(description = "New quantity", required = true) int quantity) {
        UpdateCartItemRequest request = UpdateCartItemRequest.builder()
                .quantity(quantity)
                .build();
        return toJson(cartService.updateItem(id, request));
    }

    @McpTool(name = "cart_remove_item", description = "Remove one item from the cart")
    public String removeCartItem(
            @McpToolParam(description = "Cart item ID", required = true) long id) {
        cartService.removeItem(id);
        return toJson("Cart item " + id + " removed");
    }

    @McpTool(name = "cart_clear", description = "Remove all items from the cart")
    public String clearCart() {
        cartService.clearCart();
        return toJson("Cart cleared");
    }
}
