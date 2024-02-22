package com.example.ShoppingCartProject.exceptions;

public class ProductNotFoundException extends RuntimeException {
    String name;
    public ProductNotFoundException(String name) {
        super(name + " not found in inventory");
        this.name = name;
    }
}
