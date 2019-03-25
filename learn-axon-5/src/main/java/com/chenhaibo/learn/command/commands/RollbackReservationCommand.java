package com.chenhaibo.learn.command.commands;

import com.chenhaibo.learn.common.domain.OrderId;

public class RollbackReservationCommand {

    private OrderId orderId;
    private String productId;
    private int number;

    public RollbackReservationCommand(OrderId orderId, String productId, int number) {
        this.orderId = orderId;
        this.productId = productId;
        this.number = number;
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public String getProductId() {
        return productId;
    }

    public int getNumber() {
        return number;
    }
}
