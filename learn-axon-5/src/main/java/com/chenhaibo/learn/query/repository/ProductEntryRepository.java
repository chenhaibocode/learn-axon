package com.chenhaibo.learn.query.repository;

import com.chenhaibo.learn.query.entries.ProductEntry;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "products", path = "products")
public interface ProductEntryRepository extends MongoRepository<ProductEntry, String> {

}
