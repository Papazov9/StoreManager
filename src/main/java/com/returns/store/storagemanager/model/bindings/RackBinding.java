package com.returns.store.storagemanager.model.bindings;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class RackBinding {

    @NotNull
    @Size(min = 1, max = 2, message = "At least one symbol and maximum 2!")
    private String rackName;

    @NotNull
    @Positive(message = "The quantity must be positive number.")
    private Integer quantity;

    @NotNull
    private String type;

    public RackBinding() {}

    public String getRackName() {
        return rackName;
    }

    public RackBinding setRackName(String rackName) {
        this.rackName = rackName;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public RackBinding setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getType() {
        return type;
    }

    public RackBinding setType(String type) {
        this.type = type;
        return this;
    }
}
