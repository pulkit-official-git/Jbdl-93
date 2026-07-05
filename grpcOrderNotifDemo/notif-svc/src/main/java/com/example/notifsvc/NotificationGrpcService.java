package com.example.notifsvc;

import com.example.grpc.notif.NotificationRequest;
import com.example.grpc.notif.NotificationResponse;
import com.example.grpc.notif.NotificationServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcService
public class NotificationGrpcService extends NotificationServiceGrpc.NotificationServiceImplBase {

    private static final Logger logger = LoggerFactory.getLogger(NotificationGrpcService.class);

    @Override
    public void sendNotification(NotificationRequest request, StreamObserver<NotificationResponse> responseObserver) {
        logger.info("Received gRPC notification for orderId={}, message={}",
                request.getOrderId(), request.getMessage());

        NotificationResponse response = NotificationResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Notification sent for order " + request.getOrderId())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
