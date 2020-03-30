package com.berezovska.petstore.view.initialization;

import com.berezovska.petstore.view.implementation.PetRequestImpl;
import com.berezovska.petstore.view.implementation.RequestType;

public class PetRequestInit implements RequestInitialization {
    @Override
    public RequestType requestType() {
        return new PetRequestImpl<>();
    }
}
