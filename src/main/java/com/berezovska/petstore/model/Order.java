package com.berezovska.petstore.model;

import java.time.LocalDate;

public class Order implements Entity{
    private long id;
    private long petId;
    private int quantity;
    private LocalDate shipDate;
    private OrderStatus status;
    private boolean complete = false;

    public Order () {}
    public Order(long id, long petId, int quantity, LocalDate shipDate, OrderStatus status, boolean complete) {
        this.id = id;
        this.petId = petId;
        this.quantity = quantity;
        this.shipDate = shipDate;
        this.status = status;
        this.complete = complete;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", petId=" + petId +
                ", quantity=" + quantity +
                ", shipDate=" + shipDate +
                ", status=" + status +
                ", complete=" + complete +
                '}';
    }

    @Override
    public String getPath() {
        return "/v2/store/order";
    }


    public String getStorePath() {
        return "/v2/store";
    }
}