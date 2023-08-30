package com.returns.store.storagemanager.model.view;

import java.time.LocalDateTime;

public class SoldProductsView {

    private Long id;
    private String returnItemId;
    private String condition;


    private String department;

    private String category;

    private String subCategory;

    private String asin;

    private String ean;

    private String description;


    private String currencyCode;


    private Double totalRetail;
    private String lpn;

    private String shortenDescription;

    private LocalDateTime saleTime;
    private String saleTimeString;
    private String previousRackPosition;

    public SoldProductsView() {
    }

    public Long getId() {
        return id;
    }

    public SoldProductsView setId(Long id) {
        this.id = id;
        return this;
    }

    public String getReturnItemId() {
        return returnItemId;
    }

    public SoldProductsView setReturnItemId(String returnItemId) {
        this.returnItemId = returnItemId;
        return this;
    }

    public String getCondition() {
        return condition;
    }

    public SoldProductsView setCondition(String condition) {
        this.condition = condition;
        return this;
    }

    public String getDepartment() {
        return department;
    }

    public SoldProductsView setDepartment(String department) {
        this.department = department;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public SoldProductsView setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public SoldProductsView setSubCategory(String subCategory) {
        this.subCategory = subCategory;
        return this;
    }

    public String getAsin() {
        return asin;
    }

    public SoldProductsView setAsin(String asin) {
        this.asin = asin;
        return this;
    }

    public String getEan() {
        return ean;
    }

    public SoldProductsView setEan(String ean) {
        this.ean = ean;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SoldProductsView setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public SoldProductsView setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
        return this;
    }

    public Double getTotalRetail() {
        return totalRetail;
    }

    public SoldProductsView setTotalRetail(Double totalRetail) {
        this.totalRetail = totalRetail;
        return this;
    }

    public String getLpn() {
        return lpn;
    }

    public SoldProductsView setLpn(String lpn) {
        this.lpn = lpn;
        return this;
    }

    public LocalDateTime getSaleTime() {
        return saleTime;
    }

    public SoldProductsView setSaleTime(LocalDateTime saleTime) {
        this.saleTime = saleTime;
        return this;
    }

    public String getSaleTimeString() {
        return saleTimeString;
    }

    public SoldProductsView setSaleTimeString(LocalDateTime saleDateTime) {
        int monthValue = saleDateTime.getMonthValue();
        int dayOfMonth = saleDateTime.getDayOfMonth();
        int year = saleDateTime.getYear();

        int hour = saleDateTime.getHour();
        int minute = saleDateTime.getMinute();
        int second = saleDateTime.getSecond();

        this.saleTimeString = String.format("%02d/%02d/%d %02d:%02d:%02d", dayOfMonth, monthValue, year, hour, minute, second);
        return this;
    }

    public String getShortenDescription() {
        return shortenDescription;
    }

    public SoldProductsView setShortenDescription(String shortenDescription) {
        if (shortenDescription.length() > 20) {
            this.shortenDescription = shortenDescription.substring(0, 21) + "...";
        } else {
            this.shortenDescription = shortenDescription;
        }
        return this;
    }

    public String getPreviousRackPosition() {
        return previousRackPosition;
    }

    public SoldProductsView setPreviousRackPosition(String rackName, Integer rackNumber) {
        this.previousRackPosition = rackName + ":" + rackNumber;
        return this;
    }
}
