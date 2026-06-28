package com.example.jbdl93ordersvc;

import java.util.Map;

public class CreateOrderRequest {

    public String orderId;

    Map<String,String> orderDetails;

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
