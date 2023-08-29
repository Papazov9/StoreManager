package com.returns.store.storagemanager.model.entity;

import com.returns.store.storagemanager.model.enums.SizeEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "products_for_sale")
public class SellingProduct extends Product{

    @Column(name = "rack_number")
    private Integer rackNumber;

    @Column(name = "rack_name")
    private String rackName;

    @Column(name = "sold")
    private Boolean sold;

    @Column(name = "sale_time")
    private LocalDateTime saleTime;

    @Column
    private SizeEnum size;
    public SellingProduct() {
        this.sold = false;
    }

    public Boolean isSold() {
        return sold;
    }

    public SellingProduct setSold(Boolean sold) {
        this.sold = sold;
        return this;
    }

    public LocalDateTime getSaleTime() {
        return saleTime;
    }

    public SellingProduct setSaleTime(LocalDateTime saleTime) {
        this.saleTime = saleTime;
        return this;
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

    public Boolean getSold() {
        return sold;
    }

    public SizeEnum getSize() {
        return size;
    }

    public SellingProduct setSize(SizeEnum size) {
        this.size = size;
        return this;
    }

    public String getRackNameAndNumber() {
        return String.format("%s%s",this.rackName, this.rackNumber);
    }
}
