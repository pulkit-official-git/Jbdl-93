package com.example.notificationrpc.service;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import notification.NotificationResponse;
import notification.NotificationServiceGrpc;
import notification.OrderRequest;

@GrpcService
public class NotificationGrpcService extends NotificationServiceGrpc.NotificationServiceImplBase {

    @Override
    public void sendNotification(OrderRequest request,
                                 StreamObserver<NotificationResponse> responseObserver) {

        System.out.println("========== ORDER RECEIVED ==========");
        System.out.println("Order Id : " + request.getOrderId());
        System.out.println("Customer : " + request.getCustomerName());
        System.out.println("Product  : " + request.getProductName());

        NotificationResponse response =
                NotificationResponse.newBuilder()
                        .setSuccess(true)
                        .setMessage("Notification Sent Successfully")
                        .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}