package com.example.ordersvc;

import java.util.Map;

public class CreateOrderRequest {

    private String orderId;
    private Map<String, String> orderDetails;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Map<String, String> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Map<String, String> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
