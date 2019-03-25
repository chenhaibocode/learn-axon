package com.chenhaibo.learn.query.repository;

import com.chenhaibo.learn.query.entries.OrderEntry;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "orders", path = "orders")
public interface OrderEntryRepository extends MongoRepository<OrderEntry, String> {
}
