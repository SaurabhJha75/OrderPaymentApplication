package com.example.consumer.payment.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.producer.order.model.OrderRequest;

@Service
public class PaymentConsumer {

	@RabbitListener(queues = "OrderQueue")
    public void processPayment(OrderRequest orderRequest) {
        if ("PLACED".equalsIgnoreCase(orderRequest.getOrderStatus())) {
            System.out.println("Order ID: " + orderRequest.getOrderId() + " validated. Processing payment...");
            orderRequest.setOrderStatus("PAID");
        } else {
            System.out.println("Order validation failed for Order ID: " + orderRequest.getOrderId());
            orderRequest.setOrderStatus("FAILED");
        }

        // Simulate Payment Response
        System.out.println("Payment status for Order ID " + orderRequest.getOrderId() + ": " + orderRequest.getOrderStatus());
    }
}
