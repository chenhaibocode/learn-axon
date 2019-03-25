package com.chenhaibo.learn.command.commands;

import com.chenhaibo.learn.common.domain.OrderId;

public class RollbackOrderCommand {
    private OrderId orderId;

    public RollbackOrderCommand(OrderId orderId) {
        this.orderId = orderId;
    }

    public OrderId getOrderId() {
        return orderId;
    }
}
