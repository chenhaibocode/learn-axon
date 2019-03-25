package com.chenhaibo.learn.command.saga;

import com.chenhaibo.learn.command.commands.ConfirmOrderCommand;
import com.chenhaibo.learn.command.commands.ReserveProductCommand;
import com.chenhaibo.learn.command.commands.RollbackOrderCommand;
import com.chenhaibo.learn.command.commands.RollbackReservationCommand;
import com.chenhaibo.learn.common.domain.OrderId;
import com.chenhaibo.learn.common.domain.OrderProduct;
import com.chenhaibo.learn.common.event.*;
import com.chenhaibo.learn.common.exception.OrderCreateFailedException;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.saga.EndSaga;
import org.axonframework.eventhandling.saga.SagaEventHandler;
import org.axonframework.eventhandling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.slf4j.LoggerFactory.getLogger;

@Saga
public class OrderSaga {

    private static final Logger LOGGER = getLogger(OrderSaga.class);

    private OrderId orderIdentifier;
    private ConcurrentHashMap<String, OrderProduct> toReserve;
    private Map<String, OrderProduct> toRollback;
    private int toReserveNumber;
    private boolean needRollback;

    @Autowired
    private transient CommandGateway commandGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "orderId")
    public void handle(OrderCreatedEvent event) {
        this.orderIdentifier = event.getOrderId();
        this.toReserve = event.getProducts();
        toRollback = new HashMap<>();
        toReserveNumber = toReserve.size();
        event.getProducts().forEach((id, product) -> {
            ReserveProductCommand command = new ReserveProductCommand(orderIdentifier, id, product.getAmount());
            commandGateway.send(command);
        });
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void handle(ProductNotEnoughEvent event) {
        LOGGER.info("No enough item to buy");
        toReserveNumber--;
        needRollback = true;
        if (toReserveNumber == 0)
            tryFinish();
    }

    private void tryFinish() {
        if (needRollback) {
            toReserve.forEach((id, product) -> {
                if (!product.isReserved())
                    return;
                toRollback.put(id, product);
                commandGateway.send(new RollbackReservationCommand(orderIdentifier, id, product.getAmount()));
            });
            if (toRollback.isEmpty())
                commandGateway.send(new RollbackOrderCommand(orderIdentifier));
            return;
        }
        commandGateway.send(new ConfirmOrderCommand(orderIdentifier));
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void handle(ReserveCancelledEvent event) {
        toRollback.remove(event.getProductId());
        if (toRollback.isEmpty())
            commandGateway.send(new RollbackOrderCommand(event.getOrderId()));
    }

    @SagaEventHandler(associationProperty = "id", keyName = "orderId")
    @EndSaga
    public void handle(OrderCancelledEvent event) throws OrderCreateFailedException {
        LOGGER.info("Order {} is cancelled", event.getId());
        // throw exception here will not cause the onFailure() method in the command callback
        //throw new OrderCreateFailedException("Not enough product to reserve!");
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void handle(ProductReservedEvent event) {
        OrderProduct reservedProduct = toReserve.get(event.getProductId());
        reservedProduct.setReserved(true);
        toReserveNumber--;
        //Q: will a concurrent issue raise?
        if (toReserveNumber == 0)
            tryFinish();
    }

    @SagaEventHandler(associationProperty = "id", keyName = "orderId")
    @EndSaga
    public void handle(OrderConfirmedEvent event) throws InterruptedException {
        LOGGER.info("Order {} is confirmed", event.getId());
        //Thread.sleep(10000);
    }

}
