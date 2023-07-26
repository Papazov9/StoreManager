package com.returns.store.storagemanager.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "products_for_sale")
public class SellingProduct extends Product{
    @Column(name = "product_condition")
    private String condition;

    @Column(name = "pallet_Id")
    private String palletId;

    @Column(name = "department")
    private String department;

    @Column(name = "category")
    private String category;

    @Column(name = "sub_category")
    private String subCategory;

    @Column(name = "asin")
    private String asin;

    @Column(name = "ean")
    private String ean;

    @Column(name = "product_description", columnDefinition = "LONGTEXT")
    private String description;

    @Column(name = "quantity", nullable = false)
    @PositiveOrZero
    private Long quantity;

    @Column(name = "currency_code", nullable = false)
    private String currencyCode;

    @Column(name = "total_retail", nullable = false, columnDefinition = "DECIMAL(19,4)")
    @Positive
    private Double totalRetail;

    @Column(name = "lpn")
    private String lpn;

    public SellingProduct() {

    }

    public String getCondition() {
        return condition;
    }

    public SellingProduct setCondition(String condition) {
        this.condition = condition;
        return this;
    }

    public String getPalletId() {
        return palletId;
    }

    public SellingProduct setPalletId(String palletId) {
        this.palletId = palletId;
        return this;
    }

    public String getDepartment() {
        return department;
    }

    public SellingProduct setDepartment(String department) {
        this.department = department;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public SellingProduct setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public SellingProduct setSubCategory(String subCategory) {
        this.subCategory = subCategory;
        return this;
    }

    public String getAsin() {
        return asin;
    }

    public SellingProduct setAsin(String asin) {
        this.asin = asin;
        return this;
    }

    public String getEan() {
        return ean;
    }

    public SellingProduct setEan(String ean) {
        this.ean = ean;
        return this;
    }


    public String getDescription() {
        return description;
    }

    public SellingProduct setDescription(String description) {
        this.description = description;
        return this;
    }
    public Long getQuantity() {
        return quantity;
    }

    public SellingProduct setQuantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public SellingProduct setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
        return this;
    }

    public Double getTotalRetail() {
        return totalRetail;
    }

    public SellingProduct setTotalRetail(Double totalRetail) {
        this.totalRetail = totalRetail;
        return this;
    }

    public String getLpn() {
        return lpn;
    }

    public SellingProduct setLpn(String lpn) {
        this.lpn = lpn;
        return this;
    }
}
