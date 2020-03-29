package com.berezovska.petstore.model;

import java.time.LocalDate;

public class Order implements Entity{
    private long id;
    private long petId;

    public Order () {}
    public Order(long petId, long quantity, LocalDate shipDate, OrderStatus status, boolean complete) {
        this.petId = petId;
        this.quantity = quantity;
        this.shipDate = shipDate;
        this.status = status;
        this.complete = complete;
    }

    private long quantity;
    private LocalDate shipDate;
    private OrderStatus status;
    private boolean complete = false;

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
}