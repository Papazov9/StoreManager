package com.returns.store.storagemanager.model.view;

public class RackViewResponseEntity {

    private String rackName;

    private Integer rackNumber;

    public RackViewResponseEntity() {}

    public String getRackName() {
        return rackName;
    }

    public RackViewResponseEntity setRackName(String rackName) {
        this.rackName = rackName;
        return this;
    }

    public Integer getRackNumber() {
        return rackNumber;
    }

    public RackViewResponseEntity setRackNumber(Integer rackNumber) {
        this.rackNumber = rackNumber;
        return this;
    }
}
