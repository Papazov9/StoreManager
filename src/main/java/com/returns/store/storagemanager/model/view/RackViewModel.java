package com.returns.store.storagemanager.model.view;

import com.returns.store.storagemanager.model.enums.SizeEnum;

public class RackViewModel {

    private String rackName;

    private Integer quantity;

    private SizeEnum size;

    public  RackViewModel() {}

    public String getRackName() {
        return rackName;
    }

    public RackViewModel setRackName(String rackName) {
        this.rackName = rackName;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public RackViewModel setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public SizeEnum getSize() {
        return size;
    }

    public RackViewModel setSize(SizeEnum size) {
        this.size = size;
        return this;
    }
}
