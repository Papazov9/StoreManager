package com.returns.store.storagemanager.model.view;

public class ProductViewModel {

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

    private String lpn;

    private Double totalRetail;
    private String shortenDescription;

    public String getShortenDescription() {
        return shortenDescription;
    }

    public ProductViewModel setShortenDescription(String shortenDescription) {
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

    public ProductViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getReturnItemId() {
        return returnItemId;
    }

    public ProductViewModel setReturnItemId(String returnItemId) {
        this.returnItemId = returnItemId;
        return this;
    }

    public String getCondition() {
        return condition;
    }

    public ProductViewModel setCondition(String condition) {
        this.condition = condition;
        return this;
    }

    public String getPalletId() {
        return palletId;
    }

    public ProductViewModel setPalletId(String palletId) {
        this.palletId = palletId;
        return this;
    }

    public String getDepartment() {
        return department;
    }

    public ProductViewModel setDepartment(String department) {
        this.department = department;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public ProductViewModel setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public ProductViewModel setSubCategory(String subCategory) {
        this.subCategory = subCategory;
        return this;
    }

    public String getAsin() {
        return asin;
    }

    public ProductViewModel setAsin(String asin) {
        this.asin = asin;
        return this;
    }

    public String getEan() {
        return ean;
    }

    public ProductViewModel setEan(String ean) {
        this.ean = ean;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public Long getQuantity() {
        return quantity;
    }

    public ProductViewModel setQuantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public ProductViewModel setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
        return this;
    }

    public Double getTotalRetail() {
        return totalRetail;
    }

    public ProductViewModel setTotalRetail(Double totalRetail) {
        this.totalRetail = totalRetail;
        return this;
    }

    public String getLpn() {
        return lpn;
    }

    public ProductViewModel setLpn(String lpn) {
        this.lpn = lpn;
        return this;
    }

    public ProductViewModel() {
    }

    @Override
    public String toString() {
        return "ProductViewModel{" +
                "id=" + id +
                ", returnItemId='" + returnItemId + '\'' +
                ", condition='" + condition + '\'' +
                ", palletId='" + palletId + '\'' +
                ", department='" + department + '\'' +
                ", category='" + category + '\'' +
                ", subCategory='" + subCategory + '\'' +
                ", asin='" + asin + '\'' +
                ", ean='" + ean + '\'' +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ", currencyCode='" + currencyCode + '\'' +
                ", totalRetail=" + totalRetail +
                ", shortenDescription='" + shortenDescription + '\'' +
                ", lpn='" + lpn + '\'' +
                '}';
    }
}
