package com.chenhaibo.learn.command.handlers;

import com.chenhaibo.learn.command.aggregates.ProductAggregate;
import com.chenhaibo.learn.command.commands.ReserveProductCommand;
import com.chenhaibo.learn.command.commands.RollbackReservationCommand;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.Aggregate;
import org.axonframework.commandhandling.model.Repository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.slf4j.LoggerFactory.getLogger;

@Component
public class ProductHandler {

    private static final Logger LOGGER = getLogger(ProductHandler.class);

    @Autowired
    private Repository<ProductAggregate> repository;

    @CommandHandler
    public void on(ReserveProductCommand command) {
        Aggregate<ProductAggregate> aggregate = repository.load(command.getProductId());
        aggregate.execute(aggregateRoot -> aggregateRoot.reserve(command.getOrderId(), command.getNumber()));
    }

    @CommandHandler
    public void on(RollbackReservationCommand command) {
        Aggregate<ProductAggregate> aggregate = repository.load(command.getProductId());
        aggregate.execute(aggregateRoot -> aggregateRoot.cancellReserve(command.getOrderId(), command.getNumber()));
    }
}
