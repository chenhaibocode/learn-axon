package com.chenhaibo.learn.common.event;

import com.chenhaibo.learn.common.domain.OrderId;

public class OrderCancelledEvent {
    private OrderId id;

    public OrderCancelledEvent(OrderId id) {
        this.id = id;
    }

    public OrderId getId() {
        return id;
    }
}
