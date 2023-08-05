package com.returns.store.storagemanager.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "scrap_products")
public class ScrapProduct extends Product{
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

    @Column(name = "ean")
    private String ean;

    public ScrapProduct() {

    }

    public String getEan() {
        return ean;
    }

    public ScrapProduct setEan(String ean) {
        this.ean = ean;
        return this;
    }

    public String getCondition() {
        return condition;
    }

    public ScrapProduct setCondition(String condition) {
        this.condition = condition;
        return this;
    }

    public String getPalletId() {
        return palletId;
    }

    public ScrapProduct setPalletId(String palletId) {
        this.palletId = palletId;
        return this;
    }

    public String getDepartment() {
        return department;
    }

    public ScrapProduct setDepartment(String department) {
        this.department = department;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public ScrapProduct setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public ScrapProduct setSubCategory(String subCategory) {
        this.subCategory = subCategory;
        return this;
    }

    public String getAsin() {
        return asin;
    }

    public ScrapProduct setAsin(String asin) {
        this.asin = asin;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ScrapProduct setDescription(String description) {
        this.description = description;
        return this;
    }

    public Long getQuantity() {
        return quantity;
    }

    public ScrapProduct setQuantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public ScrapProduct setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
        return this;
    }

    public Double getTotalRetail() {
        return totalRetail;
    }

    public ScrapProduct setTotalRetail(Double totalRetail) {
        this.totalRetail = totalRetail;
        return this;
    }

    public String getLpn() {
        return lpn;
    }

    public ScrapProduct setLpn(String lpn) {
        this.lpn = lpn;
        return this;
    }
}
