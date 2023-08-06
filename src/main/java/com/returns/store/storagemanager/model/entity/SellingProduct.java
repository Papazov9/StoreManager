package com.returns.store.storagemanager.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "products_for_sale")
public class SellingProduct extends Product{

    @Column(name = "rack_number")
    private Integer rackNumber;

    @Column(name = "rack_name")
    private String rackName;

    public SellingProduct() {

    }

    public Integer getRackNumber() {
        return rackNumber;
    }

    public SellingProduct setRackNumber(Integer rackNumber) {
        this.rackNumber = rackNumber;
        return this;
    }

    public String getRackName() {
        return rackName;
    }

    public SellingProduct setRackName(String rackName) {
        this.rackName = rackName;
        return this;
    }

    public String getRackNameAndNumber() {
        return String.format("%s%s",this.rackName, this.rackNumber);
    }
}
