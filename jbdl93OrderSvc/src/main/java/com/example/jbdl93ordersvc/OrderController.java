package com.example.jbdl93ordersvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    RestTemplate restTemplate;

    @PostMapping("/create")
    public String createOrder(@RequestBody CreateOrderRequest createOrderRequest){

        String id = UUID.randomUUID().toString();

        /*
        * Here we are doing all our svc repo saving before returning response
        * */

        this.restTemplate.getForObject("http://jbdl93NotifSvc/notif", String.class);

        return id;

    }
}
