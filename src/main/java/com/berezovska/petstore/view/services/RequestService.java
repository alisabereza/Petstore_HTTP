package com.berezovska.petstore.view.services;

import com.berezovska.petstore.controller.util.RequestCommand;
import com.berezovska.petstore.model.PetStatus;

import java.util.Optional;
import java.util.Scanner;

public class RequestService {
    public static Scanner sc = new Scanner(System.in);

    public static long getLongId() {
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

    public static PetStatus getPetStatus (){
        System.out.println(" Enter Pet status: ");
        String status = sc.nextLine();
            Optional<PetStatus> petStatus = PetStatus.getPetStatus(status);
            return petStatus.orElseThrow(() -> new IllegalArgumentException("Status value is wrong, choose the correct one"));
        }
        
        public static EntityType selectEntity () {
            System.out.println("Choose entity: ");
            System.out.println("Type: 'pet', 'user' or 'order': ");
            EntityType entityType = null;
            try {entityType = EntityType.getType(sc.nextLine()).orElseThrow(()-> new IllegalArgumentException("Incorrect input, try again"));}
            catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                selectEntity();
            }
            return entityType;
        }

    public static RequestCommand selectPetCommand() {
        System.out.println("Select command from: 'get', 'put', 'post', 'delete': ");
        RequestCommand command = null;
        try {command = RequestCommand.getCommand(sc.nextLine()).orElseThrow(()-> new IllegalArgumentException("Incorrect input, try again"));}
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            selectPetCommand();
        }
        return command;
    }

    public static boolean exitOption() {
        System.out.println("To exit, type 'exit', or anything to proceed : ");
        switch (sc.nextLine()) {
            case "exit": return true;
            default: return false;
        }
    }
}

