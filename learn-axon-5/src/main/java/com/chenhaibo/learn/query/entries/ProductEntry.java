package com.chenhaibo.learn.query.entries;

import org.springframework.data.annotation.Id;

public class ProductEntry {

    @Id
    private String id;
    private String name;
    private long price;
    private int stock;

    public ProductEntry() {
    }

    public ProductEntry(String id, String name, long price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
