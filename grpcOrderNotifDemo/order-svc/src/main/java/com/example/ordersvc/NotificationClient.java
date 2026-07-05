package com.example.ordersvc;

import com.example.grpc.notif.NotificationRequest;
import com.example.grpc.notif.NotificationResponse;
import com.example.grpc.notif.NotificationServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NotificationClient {

    private static final Logger logger = LoggerFactory.getLogger(NotificationClient.class);

    @GrpcClient("notif-svc")
    private NotificationServiceGrpc.NotificationServiceBlockingStub notificationStub;

    public NotificationResponse sendOrderCreatedNotification(String orderId) {
        NotificationRequest request = NotificationRequest.newBuilder()
                .setOrderId(orderId)
                .setMessage("Your order has been created successfully!")
                .build();

        NotificationResponse response = notificationStub.sendNotification(request);
        logger.info("Notif-svc responded: success={}, message={}",
                response.getSuccess(), response.getMessage());
        return response;
    }
}
