package com.chenhaibo.learn.common.event;

import com.chenhaibo.learn.common.domain.OrderId;

public class ReserveCancelledEvent {

    private OrderId orderId;
    private String productId;
    private int amount;

    public ReserveCancelledEvent(OrderId orderId, String productId, int amount) {
        this.orderId = orderId;
        this.productId = productId;
        this.amount = amount;
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public String getProductId() {
        return productId;
    }

    public int getAmount() {
        return amount;
    }
}
