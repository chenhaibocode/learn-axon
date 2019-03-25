package com.chenhaibo.learn.common.event;

import com.chenhaibo.learn.common.domain.OrderId;

public class OrderConfirmedEvent {
    private OrderId id;

    public OrderConfirmedEvent(OrderId id) {
        this.id = id;
    }

    public OrderId getId() {
        return id;
    }
}
