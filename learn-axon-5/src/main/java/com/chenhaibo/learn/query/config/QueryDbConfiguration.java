package com.chenhaibo.learn.query.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

@Configuration
public class QueryDbConfiguration extends AbstractMongoConfiguration {

    @Autowired
    private MongoClient mongoClient;

    @Value("${mongodb.dbname}")
    private String mongoDbName;

    @Override
    protected String getDatabaseName() {
        return mongoDbName;
    }

    @Override
    public Mongo mongo() throws Exception {
        return mongoClient;
    }
}