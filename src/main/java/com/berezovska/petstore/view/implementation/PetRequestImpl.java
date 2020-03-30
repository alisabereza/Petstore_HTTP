package com.berezovska.petstore.view.implementation;

import com.berezovska.petstore.model.*;
import com.berezovska.petstore.view.services.RequestService;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class PetRequestImpl<T extends Entity> extends GenericRequest<Pet> implements RequestType {
public Scanner scanner = new Scanner(System.in);
    @Override
    public void getType() {
        long id;
        PetStatus petStatus;
        Pet pet;
        String path;
        System.out.println("Select option: get pet by ID: type 'id', get pet by status: type 'status': ");
        String answer = scanner.nextLine();
        //scanner.nextLine();
        switch (answer){
            case "id":

                id = RequestService.getLongId();
                path = "/v2/pet/" + id;
                pet = getEntityByPath(path, Pet.class);
                System.out.println(pet);
                break;
            case "status":
                petStatus = RequestService.getPetStatus();
                path = "/v2/pet/findByStatus?status=" + petStatus.getStatus();
                List<Pet> petList = getListEntity(path, new TypeToken<List<Pet>>(){}.getType());
                petList.stream()
                        .sorted(Comparator.comparingLong(p -> p.getId()))
                        .forEach(System.out::println);
                break;
            default:
                System.out.println("Incorrect input");
                return;
        }

    }

    @Override
    public void postType() {
        Pet pet = new Pet();
        System.out.println("Fill the pet");
        pet = fillPet();
         String s = postEntity(pet);
        System.out.println(s);
    }

    @Override
    public void putType(){
        System.out.println("Update pet with id: ");
        Long petId = RequestService.getLongId();
        try {Pet pet = getEntityByPath("/v2/pet/" + petId, Pet.class);
            System.out.println("Enter new name: ");
            pet.setName(scanner.nextLine());
            String s = putEntity(pet);
            System.out.println(s);}
        catch (NullPointerException e) {
            System.out.println(String.format("Pet with ID %s was not found", petId));
        }
    }

    @Override
    public void deleteType() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Api-Key :> ");
        String apiKey = scanner.nextLine();

        System.out.println();
        long id = RequestService.getLongId();
        String path = new Pet().getPath() + "/" + id;
        System.out.println();
        String s = deleteEntity(path, apiKey);
        System.out.println(s);
    }


    private Pet fillPet(){
        Pet pet= new Pet();
        System.out.println("Enter pet category: ");
        String categoryName = scanner.nextLine();
        System.out.println("Enter pet name: ");
        String petName = scanner.nextLine();
        pet = new Pet(0, petName, new Category(0, categoryName), null, null, PetStatus.AVAILABLE);
        return pet;
    }

}
