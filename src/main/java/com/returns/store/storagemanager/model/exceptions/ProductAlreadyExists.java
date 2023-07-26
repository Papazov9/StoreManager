package com.returns.store.storagemanager.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductAlreadyExists extends RuntimeException {

    private final String id;

    private final String message;

    public ProductAlreadyExists(String message, String id) {
        this.id = id;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    @Override
    public String getMessage() {
        return String.format(message, id);
    }
}
