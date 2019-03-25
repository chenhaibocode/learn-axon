package com.chenhaibo.learn.query.entries;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.concurrent.ConcurrentHashMap;

@Document
public class OrderEntry {
    @Id
    private String id;
    private String username;
    private double payment;
    private String status = "reserving";
    private ConcurrentHashMap<String, OrderProductEntry> products;

    public OrderEntry() {
    }

    public OrderEntry(String id, String username, ConcurrentHashMap<String, OrderProductEntry> products) {
        this.id = id;
        this.username = username;
        this.payment = payment;
        this.products = products;
    }

    public String getId() {
        return id.toString();
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public ConcurrentHashMap<String, OrderProductEntry> getProducts() {
        return products;
    }

    public void setProducts(ConcurrentHashMap<String, OrderProductEntry> products) {
        this.products = products;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
