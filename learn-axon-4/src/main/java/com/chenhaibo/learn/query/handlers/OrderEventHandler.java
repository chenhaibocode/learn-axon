package com.chenhaibo.learn.query.handlers;

import com.chenhaibo.learn.common.event.OrderCreatedEvent;
import com.chenhaibo.learn.query.entries.OrderEntry;
import com.chenhaibo.learn.query.entries.OrderProductEntry;
import com.chenhaibo.learn.query.repository.OrderEntryRepository;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

@Component
public class OrderEventHandler {

    private static final Logger LOGGER = getLogger(OrderEventHandler.class);

    @Autowired
    private OrderEntryRepository repository;

    @EventHandler
    public void on(OrderCreatedEvent event) {
        Map<String, OrderProductEntry> map = new HashMap<>();
        event.getProducts().forEach((id, product) -> {
            map.put(id,
                    new OrderProductEntry(
                            product.getId(),
                            product.getName(),
                            product.getPrice(),
                            product.getAmount()));
        });
        OrderEntry order = new OrderEntry(event.getOrderId().toString(), event.getUsername(), map);
        repository.save(order);
    }

}
