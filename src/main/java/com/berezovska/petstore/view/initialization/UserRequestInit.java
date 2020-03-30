package com.berezovska.petstore.view.initialization;

import com.berezovska.petstore.view.implementation.RequestType;
import com.berezovska.petstore.view.implementation.UserRequestImpl;

public class UserRequestInit implements RequestInitialization{

    @Override
    public RequestType requestType() {
        return new UserRequestImpl();
    }
}
