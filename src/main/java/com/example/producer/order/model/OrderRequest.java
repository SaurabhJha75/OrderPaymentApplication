package com.example.producer.order.model;

import java.io.Serializable;

public class OrderRequest implements Serializable {

	private String orderId;
	private String orderItem;
	private double orderPrice;
	private String orderStatus;

	public OrderRequest(String orderId, String orderItem, double orderPrice, String orderStatus) {
		super();
		this.orderId = orderId;
		this.orderItem = orderItem;
		this.orderPrice = orderPrice;
		this.orderStatus = orderStatus;
	}

	public OrderRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(String orderItem) {
		this.orderItem = orderItem;
	}

	public double getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(double orderPrice) {
		this.orderPrice = orderPrice;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	@Override
	public String toString() {
		return "OrderRequest [orderId=" + orderId + ", orderItem=" + orderItem + ", orderPrice=" + orderPrice + ", orderStatus= " +orderStatus+ "]";
	}

}
