package com.berezovska.petstore.view.initialization;

import com.berezovska.petstore.view.implementation.OrderRequestImpl;
import com.berezovska.petstore.view.implementation.RequestType;

public class OrderRequestInit implements RequestInitialization {

    @Override
    public RequestType requestType() {
        return new OrderRequestImpl();
    }
}
