package com.returns.store.storagemanager.model.exceptions;

public class UnableToSaveProductToRackException extends RuntimeException {

    private Long id;

    private static final String MESSAGE = "Unable to save the product in rack with id: %d!";
    public UnableToSaveProductToRackException(Long id) {
    }

    public Long getId() {
        return id;
    }

    @Override
    public String getMessage() {
        return String.format(MESSAGE, id);
    }
}
