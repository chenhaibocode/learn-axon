package com.chenhaibo.learn.common.domain;

public class OrderProduct {
    private String id;
    private String name;
    private long price;
    private int amount;
    private boolean reserved;

    public OrderProduct() {
    }

    public OrderProduct(String id, String name, long price, int amount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
