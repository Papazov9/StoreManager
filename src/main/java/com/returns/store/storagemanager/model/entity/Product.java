package com.returns.store.storagemanager.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@MappedSuperclass
public abstract class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "return_item_id", unique = true, nullable = false)
    private String returnItemId;

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

    public Product() {

    }

    public Long getId() {
        return id;
    }

    public Product setId(Long id) {
        this.id = id;
        return this;
    }

    public String getReturnItemId() {
        return returnItemId;
    }

    public Product setReturnItemId(String returnItemId) {
        this.returnItemId = returnItemId;
        return this;
    }

    public String getCondition() {
        return condition;
    }

    public Product setCondition(String condition) {
        this.condition = condition;
        return this;
    }

    public String getPalletId() {
        return palletId;
    }

    public Product setPalletId(String palletId) {
        this.palletId = palletId;
        return this;
    }

    public String getDepartment() {
        return department;
    }

    public Product setDepartment(String department) {
        this.department = department;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public Product setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public Product setSubCategory(String subCategory) {
        this.subCategory = subCategory;
        return this;
    }

    public String getAsin() {
        return asin;
    }

    public Product setAsin(String asin) {
        this.asin = asin;
        return this;
    }

    public String getEan() {
        return ean;
    }

    public Product setEan(String ean) {
        this.ean = ean;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Product setDescription(String description) {
        this.description = description;
        return this;
    }

    public Long getQuantity() {
        return quantity;
    }

    public Product setQuantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public Product setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
        return this;
    }

    public Double getTotalRetail() {
        return totalRetail;
    }

    public Product setTotalRetail(Double totalRetail) {
        this.totalRetail = totalRetail;
        return this;
    }

    public String getLpn() {
        return lpn;
    }

    public Product setLpn(String lpn) {
        this.lpn = lpn;
        return this;
    }
}
