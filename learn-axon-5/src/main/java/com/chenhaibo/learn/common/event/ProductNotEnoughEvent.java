package com.chenhaibo.learn.common.event;

import com.chenhaibo.learn.common.domain.OrderId;

public class ProductNotEnoughEvent {

    private OrderId orderId;
    private String productId;

    public ProductNotEnoughEvent(OrderId orderId, String productId) {
        this.orderId = orderId;
        this.productId = productId;
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public String getProductId() {
        return productId;
    }
}
