package com.returns.store.storagemanager.model.enums;

public enum SizeEnum {
    SMALL("Small"),
    BIG("Big");

    private String name;
    SizeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
