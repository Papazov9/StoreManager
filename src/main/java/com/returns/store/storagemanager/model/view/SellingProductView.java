package com.returns.store.storagemanager.model.view;

public class SellingProductView {
    private Long id;
    private String returnItemId;
    private String condition;
    private String palletId;

    private String department;

    private String category;

    private String subCategory;

    private String asin;

    private String ean;

    private String description;

    private Long quantity;

    private String currencyCode;


    private Double totalRetail;
    private String lpn;

    private String shortenDescription;

    private String rackName;

    private Integer rackNumber;

    public String getShortenDescription() {
        return shortenDescription;
    }

    public SellingProductView setShortenDescription(String shortenDescription) {
        if (shortenDescription.length() > 20) {
            this.shortenDescription = shortenDescription.substring(0, 21) + "...";
        } else {
            this.shortenDescription = shortenDescription;
        }
        return this;
    }

    public Long getId() {
        return id;
    }

    public SellingProductView setId(Long id) {
        this.id = id;
        return this;
    }

    public String getReturnItemId() {
        return returnItemId;
    }

    public SellingProductView setReturnItemId(String returnItemId) {
        this.returnItemId = returnItemId;
        return this;
    }

    public String getCondition() {
        return condition;
    }

    public SellingProductView setCondition(String condition) {
        this.condition = condition;
        return this;
    }

    public String getPalletId() {
        return palletId;
    }

    public SellingProductView setPalletId(String palletId) {
        this.palletId = palletId;
        return this;
    }

    public String getDepartment() {
        return department;
    }

    public SellingProductView setDepartment(String department) {
        this.department = department;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public SellingProductView setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public SellingProductView setSubCategory(String subCategory) {
        this.subCategory = subCategory;
        return this;
    }

    public String getAsin() {
        return asin;
    }

    public SellingProductView setAsin(String asin) {
        this.asin = asin;
        return this;
    }

    public String getEan() {
        return ean;
    }

    public SellingProductView setEan(String ean) {
        this.ean = ean;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SellingProductView setDescription(String description) {
        this.description = description;
        return this;
    }

    public Long getQuantity() {
        return quantity;
    }

    public SellingProductView setQuantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public SellingProductView setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
        return this;
    }

    public Double getTotalRetail() {
        return totalRetail;
    }

    public SellingProductView setTotalRetail(Double totalRetail) {
        this.totalRetail = totalRetail;
        return this;
    }

    public String getRackName() {
        return rackName;
    }

    public SellingProductView setRackName(String rackName) {
        this.rackName = rackName;
        return this;
    }

    public Integer getRackNumber() {
        return rackNumber;
    }

    public SellingProductView setRackNumber(Integer rackNumber) {
        this.rackNumber = rackNumber;
        return this;
    }

    public String getLpn() {
        return lpn;
    }

    public SellingProductView setLpn(String lpn) {
        this.lpn = lpn;
        return this;
    }
}
