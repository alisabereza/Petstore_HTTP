package com.berezovska.petstore.model;

import java.util.List;

public class Pet implements Entity {
    private long id;
    private Category category;
    private String name;
    private String [] photoUrls;
    private Tag [] tags;
    private PetStatus status;

    public Pet() {
    }

    public Pet(long id, String name, Category category, String [] photoUrls, Tag [] tags, PetStatus status) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.photoUrls = photoUrls;
        this.tags = tags;
        this.status = status;
    }

    @Override
    public String getPath() {
        return "/v2/pet";
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public PetStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Pet {ID=" + getId() + ", Name=" + getName() + ", Category {ID=" + ((getCategory() == null) ? "" : (getCategory().getId() + ", name=" + getCategory().getName())) + "}, Status=" + getStatus().toString() + "}";
    }

    public void setStatus(String status) {
        this.status = PetStatus.getPetStatus(status).orElse(PetStatus.PENDING);
    }

    public String printPhotos(List<String> photoUrls) {
        StringBuilder sb = new StringBuilder();
        for (String string : photoUrls) {
            sb.append(string).append(", ");
        }
        return sb.toString();
    }

}
