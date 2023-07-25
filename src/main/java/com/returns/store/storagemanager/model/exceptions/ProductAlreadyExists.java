package com.returns.store.storagemanager.model.exceptions;

public class ProductAlreadyExists extends RuntimeException {

    private final Long id;

    private final String message;

    public ProductAlreadyExists(String message, Long id) {
        this.id = id;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
