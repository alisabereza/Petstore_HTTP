package com.berezovska.petstore.view.implementation;

import com.berezovska.petstore.model.ApiResponse;
import com.berezovska.petstore.model.Order;
import com.berezovska.petstore.model.OrderStatus;
import com.berezovska.petstore.view.services.RequestService;

import java.util.Scanner;

public class OrderRequestImpl extends GenericRequest<Order> implements RequestType {
    Order order;
    Scanner scanner;

    public OrderRequestImpl () {
        this.order = new Order();
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void getType() {
        System.out.println("To get Order by ID, type 'id' OR ");
        System.out.println("To get Inventory of pets, type 'inv': ");
        String answer = scanner.next();
        switch (answer) {
            case "id": {
                long orderId = RequestService.getLongId();
                String path = order.getPath() + "/" + orderId;
                order = getEntityByPath(path, Order.class);
                if (order.getId()==0) {
                    System.out.println("User not found");
                }
                else {
                    System.out.println(order);
                }
                break;
            }
            case "inv": {
                String path = order.getStorePath() + "/inventory";
                String inventory = getStringResult(path);
                System.out.println(inventory);
                break;
            }
            default: {
                System.out.println("Incorrect input, try again");
                getType();
            }
        }
    }

    @Override
    public void postType() {
        System.out.println("Enter Pet ID to order: ");
        long petId = scanner.nextLong();
        order = new Order(0, petId, 1, null, OrderStatus.PLACED, false);
        System.out.println("The following order will be ent: " + order);
        String result = postEntity(order);
        System.out.println(result);
    }

    @Override
    public void putType() {
        System.out.println("Not applicable for Order");
    }

    @Override
    public void deleteType() {
        System.out.print("Enter api key (special_key): ");
        String apiKey = scanner.next();
        System.out.println("Deleting Order by ID");
        long orderId = RequestService.getLongId();
        String path = order.getPath() + "/" + orderId;
        ApiResponse response = deleteEntity(path, apiKey);
        System.out.println(response);
    }
}
