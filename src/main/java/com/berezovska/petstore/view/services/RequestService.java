package com.berezovska.petstore.view.services;

import com.berezovska.petstore.controller.util.RequestCommand;
import com.berezovska.petstore.model.PetStatus;
import java.util.Scanner;

public class RequestService {
    public static Scanner sc = new Scanner(System.in);

    public static long getLongId() {
        boolean idProvided = false;
        long id = 0;
        while (!idProvided) {

        try {
            sc.nextLine();
            System.out.print("Enter id ");
            id = sc.nextLong();
            idProvided = true;
        } catch (Exception e) {
            System.out.println("Wrong input format, try again");
        }
        }
        return id;
    }

    public static PetStatus getPetStatus() {
        boolean petStatusSelected = false;
        PetStatus petStatus = null;
        while (!petStatusSelected) {
            System.out.println(" Enter Pet status: ");
            String status = sc.next();
            try {
                petStatus = PetStatus.getPetStatus(status).orElseThrow(() -> new IllegalArgumentException("Incorrect input, try again"));
                petStatusSelected = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return petStatus;
    }

    public static EntityType selectEntity() {
        boolean entitySelected = false;
        EntityType entityType = null;
        while (!entitySelected) {
            System.out.println("Choose entity: ");
            System.out.println("Type: 'pet', 'user' or 'order': ");
            getPause();
                String entity = sc.next();
                try {
                    entityType = EntityType.getType(entity).orElseThrow(() -> new IllegalArgumentException("Incorrect input, try again"));
                    entitySelected = true;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
        }

        return entityType;
    }

    public static RequestCommand selectCommand() {
        boolean commandSelected = false;
        RequestCommand command = null;
        while (!commandSelected) {
            System.out.println("Select command from: 'get', 'put', 'post', 'delete': ");
            getPause();
            String commandString = sc.next();
            try {
                command = RequestCommand.getCommand(commandString).orElseThrow(() -> new IllegalArgumentException("Incorrect input, try again"));
                commandSelected = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        return command;
    }

    public static boolean exitOption() {
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

