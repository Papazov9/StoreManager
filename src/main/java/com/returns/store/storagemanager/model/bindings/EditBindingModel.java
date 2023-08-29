package com.returns.store.storagemanager.model.bindings;

import jakarta.persistence.Transient;
import jakarta.validation.constraints.*;

public class EditBindingModel {
    @Transient
    private Long id;

    @NotNull
    @NotEmpty
    private String returnItemId;

    @NotNull
    @NotEmpty
    private String condition;

    @NotNull
    @NotEmpty
    private String lpn;

    @NotNull
    @NotEmpty
    private String palletId;

    @NotNull
    @NotEmpty
    private String department;

    @NotNull
    @NotEmpty
    private String category;

    @NotNull
    @NotEmpty
    private String subCategory;

    @NotNull
    @NotEmpty
    private String asin;

    @NotNull
    @NotEmpty
    private String ean;

    @NotNull
    @NotEmpty
    private String description;

    @PositiveOrZero
    private Long quantity;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 3)
    private String currencyCode;

    @Positive
    private Double totalRetail;

    @Size(min = 1, max = 1)
    private String rackName;

    @Positive
    private Integer rackNumber;

    public EditBindingModel() {}

    public Long getId() {
        return id;
    }

    public EditBindingModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getReturnItemId() {
        return returnItemId;
    }

    public EditBindingModel setReturnItemId(String returnItemId) {
        this.returnItemId = returnItemId;
        return this;
    }

    public String getCondition() {
        return condition;
    }

    public EditBindingModel setCondition(String condition) {
        this.condition = condition;
        return this;
    }

    public String getPalletId() {
        return palletId;
    }

    public EditBindingModel setPalletId(String palletId) {
        this.palletId = palletId;
        return this;
    }

    public String getDepartment() {
        return department;
    }

    public EditBindingModel setDepartment(String department) {
        this.department = department;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public EditBindingModel setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public EditBindingModel setSubCategory(String subCategory) {
        this.subCategory = subCategory;
        return this;
    }

    public String getAsin() {
        return asin;
    }

    public EditBindingModel setAsin(String asin) {
        this.asin = asin;
        return this;
    }

    public String getEan() {
        return ean;
    }

    public EditBindingModel setEan(String ean) {
        this.ean = ean;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public EditBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public Long getQuantity() {
        return quantity;
    }

    public EditBindingModel setQuantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public EditBindingModel setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
        return this;
    }

    public Double getTotalRetail() {
        return totalRetail;
    }

    public EditBindingModel setTotalRetail(Double totalRetail) {
        this.totalRetail = totalRetail;
        return this;
    }

    public String getLpn() {
        return lpn;
    }

    public EditBindingModel setLpn(String lpn) {
        this.lpn = lpn;
        return this;
    }

    public String getRackName() {
        return rackName;
    }

    public EditBindingModel setRackName(String rackName) {
        this.rackName = rackName;
        return this;
    }

    public Integer getRackNumber() {
        return rackNumber;
    }

    public EditBindingModel setRackNumber(Integer rackNumber) {
        this.rackNumber = rackNumber;
        return this;
    }
}
