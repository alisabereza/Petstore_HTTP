package com.berezovska.petstore.view.services;

import com.berezovska.petstore.controller.util.RequestCommand;
import com.berezovska.petstore.model.PetStatus;
import java.util.Optional;
import java.util.Scanner;

public class RequestService {

    public static long getLongId() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter id ");
        long id = 0;
        try {
            id = sc.nextLong();
            return id;
        } catch (Exception e) {
            System.out.println("Wrong input format");
            getLongId();
        }

        return id;
    }

    public static PetStatus getPetStatus() {
        Scanner sc = new Scanner(System.in);
        System.out.println(" Enter Pet status: ");
        String status = sc.next();
        Optional<PetStatus> petStatus = PetStatus.getPetStatus(status);

        return petStatus.orElseThrow(() -> new IllegalArgumentException("Status value is wrong, choose the correct one"));
    }

    public static EntityType selectEntity() {
        Scanner sc = new Scanner(System.in);
        String entity = "";
        System.out.println("Choose entity: ");
        System.out.println("Type: 'pet', 'user' or 'order': ");
        EntityType entityType = null;
        getPause();
        if (sc.hasNext()) {
            entity = sc.next();
            try {
                entityType = EntityType.getType(entity).orElseThrow(() -> new IllegalArgumentException("Incorrect input, try again"));
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                selectEntity();
            }
        }

        return entityType;
    }

    public static RequestCommand selectCommand() {
        Scanner sc = new Scanner(System.in);
        String commandString = "";
        RequestCommand command = null;
        System.out.println("Select command from: 'get', 'put', 'post', 'delete': ");
        getPause();
        if (sc.hasNext()) {
            commandString = sc.next();
            try {
                command = RequestCommand.getCommand(commandString).orElseThrow(() -> new IllegalArgumentException("Incorrect input, try again"));
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                selectCommand();
            }
        }

        return command;
    }

    public static boolean exitOption() {
        Scanner sc = new Scanner(System.in);
        String answer = "";
        System.out.println("To exit, type 'exit', or anything to proceed : ");
        getPause();
        if (sc.hasNext()) {
            answer = sc.next();
        }
        return "exit".equals(answer);
    }

    public static void getPause() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

}

