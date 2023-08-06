package com.returns.store.storagemanager.model.exceptions;

public class ProductNotFoundException extends RuntimeException{

    private final Long id;

    private final String message = "Product with id: %s does not exists!";
    public ProductNotFoundException(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String getMessage() {
        return String.format(message, id);
    }
}
