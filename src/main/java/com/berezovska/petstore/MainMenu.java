package com.berezovska.petstore;

import com.berezovska.petstore.controller.util.RequestCommand;
import com.berezovska.petstore.view.initialization.PetRequestInit;
import com.berezovska.petstore.view.initialization.RequestInitialization;
import com.berezovska.petstore.view.services.EntityType;
import com.berezovska.petstore.view.services.RequestService;

public class MainMenu {
    private boolean exit = false;
    private RequestInitialization requestInit;

    void getRequestMethod(){
        do {
EntityType entityType = RequestService.selectEntity();
switch (entityType) {
    case PET: requestInit = new PetRequestInit();
     RequestCommand command = RequestService.selectPetCommand();
     switch (command) {
         case GET: requestInit.requestType().getType(); break;
         case PUT: requestInit.requestType().putType(); break;
         case POST: requestInit.requestType().postType(); break;
         case DELETE: requestInit.requestType().deleteType(); break;
     }
     break;
    case SHOP: break;
    case USER: break;
    default: break;}
exit = RequestService.exitOption();

        } while (!exit);
        System.out.println("Bye!");
    }
}
