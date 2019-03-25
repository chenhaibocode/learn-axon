package com.chenhaibo.learn.command.commands;

import com.chenhaibo.learn.common.domain.OrderId;

public class ConfirmOrderCommand {

    private OrderId id;

    public ConfirmOrderCommand(OrderId id) {
        this.id = id;
    }

    public OrderId getId() {
        return id;
    }
}
