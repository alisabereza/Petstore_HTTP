package com.berezovska.petstore.view.implementation;


import com.berezovska.petstore.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserRequestImpl extends GenericRequest<User> implements RequestType {
    private User user = new User();
    Scanner scanner = new Scanner(System.in);

    @Override
    public void getType() {

        System.out.println("Enter username format is: firstName.lastName@gmail.com): ");
        String name = scanner.next();
        user = new User();
        String path = user.getPath() + "/" + name;
        user = getEntityByPath(path, User.class);
        System.out.println(user);
    }

    @Override
    public void postType() {
        String firstName = "";
        String lastName = "";
        String email = "";
        User user = null;
        List<User> users = new ArrayList<>();

        boolean userListCompleted = false;
        do {
            System.out.println("Enter user first name: ");
            firstName = scanner.next();
            System.out.println("Enter user last name: ");
            lastName = scanner.next();
            email = String.format("%s.%s@gmail.com", firstName, lastName);
            user = new User(0, email, firstName, lastName, email, "xxx", "103", 1);
            System.out.println(user);
            users.add(user);
            System.out.println("Do you want to add one more user? y/n: ");
            String answer = scanner.next();
            switch (answer) {
                case "y":
                    break;
                case "n":
                    userListCompleted = true;
                    break;
                default:
                    System.out.println("Incorrect input. User list completed");
                    userListCompleted = true;
                    break;
            }
        }
        while (!userListCompleted);
        User[] userArray = new User[users.size()];
        users.toArray(userArray);
        String result = postEntity((userArray));
        System.out.println(result);
    }

    @Override
    public void putType() {
        System.out.println("Enter username (format is: firstName.lastName@gmail.com): ");
        String firstName = scanner.next();
        System.out.println("Enter user last name: ");
        String lastName = scanner.next();
        String email = String.format("%s.%s@gmail.com", firstName, lastName);
        user = new User(0, email, firstName, lastName, email, "xxx", "103", 1);
        user.setPath(user.getPath() + "/" + user.getUsername());
        String result = putEntity(user);
        System.out.println(result);
    }

    @Override
    public void deleteType() {
        System.out.print("Enter api key (special_key): ");
        String apiKey = scanner.next();
        System.out.println("Enter username (format is: firstName.lastName@gmail.com): ");
        String name = scanner.next();
        String path = new User().getPath() + "/" + name;
        String result = deleteEntity(path, apiKey);
        System.out.println(result);
    }


    public void login() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter username");
        String name = scanner.next();
        System.out.println("Enter password");
        String pass = scanner.next();

        String ans = getStringResult(user.getPath() + "/login?username=" + name + "&password=" + pass);
        System.out.println(ans);
    }

    public void logout() {
        String ans = getStringResult(user.getPath() + "/logout");
        System.out.println(ans);
    }
}

