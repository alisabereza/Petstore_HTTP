package com.berezovska.petstore;

import com.berezovska.petstore.controller.util.RequestCommand;
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
        do {
            EntityType entityType = RequestService.selectEntity();
            RequestService.getPause();
            switch (entityType) {
                case PET: {
                    requestInit = new PetRequestInit();
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
                    }
                    break;
                }
                case SHOP:
                    break;
                case USER: {
                    requestInit = new UserRequestInit();
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
                    }
                    break;
                }
                default:
                    break;
            }
            RequestService.getPause();
            exit = RequestService.exitOption();

        } while (!exit);
        System.out.println("Bye!");
    }
}
