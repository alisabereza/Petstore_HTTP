package com.berezovska.petstore.view.implementation;

import com.berezovska.petstore.model.ApiResponse;
import com.berezovska.petstore.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserRequestImpl extends GenericRequest<User> implements RequestType {
    private User user = new User();
    Scanner scanner = new Scanner(System.in);

    @Override
    public void getType() {

        System.out.println("Enter username: ");
        String name = scanner.next();
        user = new User();
        String path = user.getPath() + "/" + name;
        user = getEntityByPath(path, User.class);
        if (user.getId()==0) {
            System.out.println("User not found");
        }
        else {
            System.out.println(user);
        }
    }

    @Override
    public void postType() {
        String firstName;
        String lastName ;
        String email ;
        user = null;
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
        System.out.println("To change user data, please login to application.");
        user = login();
        user.setPath(user.getPath() + "/" + user.getUsername());
        if (user!=null) {
            System.out.println("Select what to change: 'first_name', 'last_name', 'email', 'phone': ");
            String answer = scanner.next();

            switch (answer) {
                case "first_name": {
                    System.out.println("Enter new first name: ");
                    user.setFirstName(scanner.next());
                    break;
                }
                case "last_name": {
                    System.out.println("Enter new last name: ");
                    user.setLastName(scanner.next());
                    break;
                }
                case "email": {
                    System.out.println("Enter new email: ");
                    user.setEmail(scanner.next());
                    break;
                }
                case "phone": {
                    System.out.println("Enter new phone number: ");
                    user.setPhone(scanner.next());
                    break;
                }
                default:
                    System.out.println("Incorrect input. Try again");
                    putType();
                    break;
            }
            String result = putEntity(user);
            System.out.println(result);
            user.setPath("/v2/user");
            logout();
        }

    }

    @Override
    public void deleteType() {
        System.out.print("Enter api key (special_key): ");
        String apiKey = scanner.next();
        System.out.println("Enter username: ");
        String name = scanner.next();
        String path = new User().getPath() + "/" + name;
        ApiResponse result = deleteEntity(path, apiKey);
        System.out.println(result);
    }


    public User login() {
        System.out.println("Enter username: ");
        String name = scanner.next();
        System.out.println("Enter password: ");
        String pass = scanner.next();
        String answer = getStringResult(user.getPath() + "/login?username=" + name + "&password=" + pass);
        System.out.println(answer);
        user = getEntityByPath(user.getPath() + "/" + name, User.class);
        return user;
    }

    public void logout() {
        String answer = getStringResult(user.getPath() + "/logout");
            System.out.println(answer);
        }
    }


