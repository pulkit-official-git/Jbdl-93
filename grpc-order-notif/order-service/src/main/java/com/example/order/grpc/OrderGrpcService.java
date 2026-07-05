package com.example.order.grpc;

import com.example.order.notif.NotifServiceGrpc;
import com.example.order.notif.NotifyResponse;
import com.example.order.notif.OrderNotification;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.client.inject.GrpcClient;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

@GrpcService
public class OrderGrpcService extends OrderServiceGrpc.OrderServiceImplBase {

    private static final Logger log = LoggerFactory.getLogger(OrderGrpcService.class);

    @GrpcClient("notif-service")
    private NotifServiceGrpc.NotifServiceBlockingStub notifStub;

    @Override
    public void placeOrder(PlaceOrderRequest request, StreamObserver<PlaceOrderResponse> responseObserver) {
        String orderId = UUID.randomUUID().toString();

        log.info("Placing order {} for customer {}", orderId, request.getCustomerName());

        OrderNotification notification = OrderNotification.newBuilder()
                .setOrderId(orderId)
                .setCustomerName(request.getCustomerName())
                .setProductName(request.getProductName())
                .setQuantity(request.getQuantity())
                .setAmount(request.getAmount())
                .build();

        NotifyResponse notifyResponse = notifStub.notifyOrder(notification);

        PlaceOrderResponse response = PlaceOrderResponse.newBuilder()
                .setOrderId(orderId)
                .setStatus("PLACED")
                .setMessage(notifyResponse.getMessage())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
