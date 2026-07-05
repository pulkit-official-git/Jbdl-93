package com.example.notif.grpc;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcService
public class NotifGrpcService extends NotifServiceGrpc.NotifServiceImplBase {

    private static final Logger log = LoggerFactory.getLogger(NotifGrpcService.class);

    @Override
    public void notifyOrder(OrderNotification request, StreamObserver<NotifyResponse> responseObserver) {
        log.info("========== ORDER NOTIFICATION ==========");
        log.info("Order ID      : {}", request.getOrderId());
        log.info("Customer Name : {}", request.getCustomerName());
        log.info("Product Name  : {}", request.getProductName());
        log.info("Quantity      : {}", request.getQuantity());
        log.info("Amount        : {}", request.getAmount());
        log.info("========================================");

        NotifyResponse response = NotifyResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Notification sent for order " + request.getOrderId())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
