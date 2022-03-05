package com.github.raydeth.kafka.model;

import java.io.Serializable;
import java.util.UUID;

public class Product implements Serializable {

    private UUID id;
    private String name;
    private UUID categoryId;

    public Product(UUID id, String name, UUID categoryId) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
    }

    public Product() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(UUID categoryId) {
        this.categoryId = categoryId;
    }
}
