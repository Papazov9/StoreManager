package com.returns.store.storagemanager.model.enums;

public enum RoleEnum {

    ADMIN("Admin"),
    MEMBER("Member");

    private String name;
    RoleEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
