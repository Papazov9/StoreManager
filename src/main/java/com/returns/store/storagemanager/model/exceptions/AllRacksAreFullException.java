package com.returns.store.storagemanager.model.exceptions;

import com.returns.store.storagemanager.model.enums.SizeEnum;

public class AllRacksAreFullException extends RuntimeException {

    private SizeEnum sizeEnum;
    private static final String MESSAGE = "All racks with size: %s are full!";
    public AllRacksAreFullException(SizeEnum size) {
        this.sizeEnum = size;
    }

    public SizeEnum getSizeEnum() {
        return sizeEnum;
    }

    @Override
    public String getMessage() {
        return String.format(MESSAGE, sizeEnum.name());
    }
}
