package com.berezovska.petstore.view.services;

import com.berezovska.petstore.model.PetStatus;

import java.util.Arrays;
import java.util.Optional;

public enum EntityType {
    PET("pet"), ORDER("order"), USER("user");

    private String type;

    EntityType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static Optional<EntityType> getType(String type) {
        return Arrays.stream(EntityType.values())
                .filter(enumValue -> enumValue.getType().equals(type))
                .findAny();
    }
}
