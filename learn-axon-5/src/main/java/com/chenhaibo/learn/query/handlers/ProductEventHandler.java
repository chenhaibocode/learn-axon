package com.chenhaibo.learn.query.handlers;

import com.chenhaibo.learn.common.event.ProductCreatedEvent;
import com.chenhaibo.learn.common.event.ProductReservedEvent;
import com.chenhaibo.learn.common.event.ReserveCancelledEvent;
import com.chenhaibo.learn.query.entries.ProductEntry;
import com.chenhaibo.learn.query.repository.ProductEntryRepository;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.slf4j.LoggerFactory.getLogger;

@Component
public class ProductEventHandler {

    private static final Logger LOGGER = getLogger(ProductEventHandler.class);

    @Autowired
    ProductEntryRepository repository;

    @EventHandler
    public void on(ProductCreatedEvent event) {
        // update the data in the cache or db of the query side
        LOGGER.debug("repository data is updated");
        repository.save(new ProductEntry(event.getId(), event.getName(), event.getPrice(), event.getStock()));
    }

    @EventHandler
    public void on(ProductReservedEvent event) {
        ProductEntry product = repository.findOne(event.getProductId());
        if (product == null) {
            LOGGER.error("Cannot find product with id {}", product.getId());
            return;
        }
        product.setStock(event.getAmount());
        repository.save(product);
    }

    @EventHandler
    public void on(ReserveCancelledEvent event) {
        ProductEntry product = repository.findOne(event.getProductId());
        if (product == null) {
            LOGGER.error("Cannot find product with id {}", product.getId());
            return;
        }
        product.setStock(event.getAmount());
        repository.save(product);
    }
}
