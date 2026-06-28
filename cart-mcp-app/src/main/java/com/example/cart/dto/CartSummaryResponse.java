package com.example.cart.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartSummaryResponse {

    private int itemCount;
    private int totalQuantity;
    private BigDecimal totalAmount;
    private List<CartItemResponse> items;
}
