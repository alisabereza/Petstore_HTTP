package com.berezovska.petstore;

import com.berezovska.petstore.controller.util.RequestCommand;
import com.berezovska.petstore.view.initialization.OrderRequestInit;
import com.berezovska.petstore.view.initialization.PetRequestInit;
import com.berezovska.petstore.view.initialization.RequestInitialization;
import com.berezovska.petstore.view.initialization.UserRequestInit;
import com.berezovska.petstore.view.services.EntityType;
import com.berezovska.petstore.view.services.RequestService;

public class MainMenu {
    private boolean exit = false;
    private RequestInitialization requestInit;
    private RequestCommand command;

    void getRequestMethod() {
        EntityType entityType;
        do {
            entityType = RequestService.selectEntity();
            RequestService.getPause();
            switch (entityType) {
                case PET: {
                    requestInit = new PetRequestInit();
                    break;
                }
                case ORDER:
                    requestInit = new OrderRequestInit();

                    break;
                case USER: {
                    requestInit = new UserRequestInit();

                    break;
                }
                default:
                    System.out.println("Incorrect input, try again");
                    getRequestMethod();
                    break;
            }
            command = RequestService.selectCommand();
            switch (command) {
                case GET:
                    requestInit.requestType().getType();
                    break;
                case PUT:
                    requestInit.requestType().putType();
                    break;
                case POST:
                    requestInit.requestType().postType();
                    break;
                case DELETE:
                    requestInit.requestType().deleteType();
                    break;

                default:
                    System.out.println("Incorrect input, try again");
                    getRequestMethod();
                    break;
            }
            RequestService.getPause();
            exit = RequestService.exitOption();

        } while (!exit);
        System.out.println("Bye!");
    }
}
