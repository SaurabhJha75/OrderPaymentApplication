package com.example.producer.order.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.producer.order.model.OrderRequest;

@RestController
@RequestMapping("/api/order")
public class OrderController {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@PostMapping("/place")
	public ResponseEntity<String> placeOrder(@RequestBody OrderRequest orderRequest, @RequestHeader HttpHeaders header){
		
		if(!"true".equalsIgnoreCase(header.getFirst("Validation"))) {
			return ResponseEntity.badRequest().body("Header validation failed.");
		}
		
		// simulating order request
		orderRequest.setOrderStatus(orderRequest.getOrderStatus());
		rabbitTemplate.convertAndSend("OrderExchange", "OrderRoutingKey", orderRequest);
		
		return ResponseEntity.ok("Order placed successfully!");
	}
}
