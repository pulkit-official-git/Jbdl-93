package com.example.ordersvc;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final NotificationClient notificationClient;

    public OrderController(NotificationClient notificationClient) {
        this.notificationClient = notificationClient;
    }

    @PostMapping("/create")
    public String createOrder(@RequestBody CreateOrderRequest createOrderRequest) {
        String id = UUID.randomUUID().toString();

        // Save order to DB here in a real app.
        // Instead of RestTemplate GET, we notify via gRPC.
        notificationClient.sendOrderCreatedNotification(id);

        return id;
    }
}
