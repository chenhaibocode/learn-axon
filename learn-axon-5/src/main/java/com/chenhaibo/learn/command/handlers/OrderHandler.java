package com.chenhaibo.learn.command.handlers;

import com.chenhaibo.learn.command.aggregates.OrderAggregate;
import com.chenhaibo.learn.command.aggregates.ProductAggregate;
import com.chenhaibo.learn.command.commands.ConfirmOrderCommand;
import com.chenhaibo.learn.command.commands.CreateOrderCommand;
import com.chenhaibo.learn.command.commands.RollbackOrderCommand;
import com.chenhaibo.learn.common.domain.OrderProduct;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.Aggregate;
import org.axonframework.commandhandling.model.Repository;
import org.axonframework.eventhandling.EventBus;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

import static org.slf4j.LoggerFactory.getLogger;

@Component
public class OrderHandler {

    private static final Logger LOGGER = getLogger(OrderHandler.class);

    @Autowired
    private Repository<OrderAggregate> repository;

    @Autowired
    private Repository<ProductAggregate> productRepository;

    @Autowired
    private EventBus eventBus;

    @CommandHandler
    public void handle(CreateOrderCommand command) throws Exception {
        ConcurrentHashMap<String, OrderProduct> products = new ConcurrentHashMap<>();
        command.getProducts().forEach((productId, number) -> {
            LOGGER.debug("Loading product information with productId: {}", productId);
            Aggregate<ProductAggregate> aggregate = productRepository.load(productId);
            products.put(productId,
                    new OrderProduct(productId,
                            aggregate.invoke(productAggregate -> productAggregate.getName()),
                            aggregate.invoke(productAggregate -> productAggregate.getPrice()),
                            number));
        });
        repository.newInstance(() -> new OrderAggregate(command.getOrderId(), command.getUsername(), products));
    }

    @CommandHandler
    public void handle(RollbackOrderCommand command) {
        Aggregate<OrderAggregate> aggregate = repository.load(command.getOrderId().getIdentifier());
        aggregate.execute(aggregateRoot -> aggregateRoot.delete());
    }

    @CommandHandler
    public void handle(ConfirmOrderCommand command) {
        Aggregate<OrderAggregate> aggregate = repository.load(command.getId().getIdentifier());
        aggregate.execute(aggregateRoot -> aggregateRoot.confirm());
    }

}
