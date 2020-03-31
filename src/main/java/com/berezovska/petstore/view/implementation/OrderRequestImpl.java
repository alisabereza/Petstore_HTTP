package com.berezovska.petstore.view.implementation;

import com.berezovska.petstore.model.Order;
import com.berezovska.petstore.model.OrderStatus;
import java.util.Scanner;

public class OrderRequestImpl extends GenericRequest<Order> implements RequestType {
    Order order = null;
    Scanner scanner = new Scanner(System.in);

    @Override
    public void getType() {
        System.out.println("Enter Order ID: ");
        long orderId = scanner.nextLong();
        order = new Order();
        String path = order.getPath() + "/" + orderId;
        order = getEntityByPath(path, Order.class);
        System.out.println(order);

    }

    @Override
    public void postType() {
        System.out.println("Enter pet ID to order: ");
        long petId = scanner.nextLong();
        order = new Order(0, petId, 1, null, OrderStatus.PLACED, false);
        System.out.println("The following order will be ent: " + order);
        String path = order.getPath();
        String result = postEntity(order);
        System.out.println(result);
    }

    @Override
    public void putType() {

    }

    @Override
    public void deleteType() {

    }
}
