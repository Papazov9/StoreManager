package com.returns.store.storagemanager.model.exceptions;

import com.returns.store.storagemanager.model.enums.SizeEnum;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
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
