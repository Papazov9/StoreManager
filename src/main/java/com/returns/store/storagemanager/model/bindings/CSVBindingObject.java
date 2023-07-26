package com.returns.store.storagemanager.model.bindings;

import com.opencsv.bean.CsvBindByName;

import java.sql.Timestamp;
import java.time.LocalDate;

public class CSVBindingObject {
    @CsvBindByName(column = "CONDITION")
    private String condition;

    @CsvBindByName(column = "ReturnItemID")
    private String returnItemId;

    @CsvBindByName(column = "Pallet ID")
    private String palletId;

    @CsvBindByName(column = "DEPARTMENT")
    private String department;

    @CsvBindByName(column = "CATEGORY")
    private String category;

    @CsvBindByName(column = "SUBCATEGORY")
    private String subCategory;

    @CsvBindByName(column = "ASIN")
    private String asin;

    @CsvBindByName(column = "EAN")
    private String ean;

    @CsvBindByName(column = "Item Desc")
    private String description;

    @CsvBindByName(column = "QTY")
    private Long quantity;

    @CsvBindByName(column = "CURRENCY CODE")
    private String currencyCode;

    @CsvBindByName(column = "TOTAL RETAIL")
    private Double totalRetail;

    @CsvBindByName(column = "LPN")
    private String lpn;


    public CSVBindingObject() {
    }

    public String getCondition() {
        return condition;
    }

    public CSVBindingObject setCondition(String condition) {
        this.condition = condition;
        return this;
    }

    public String getReturnItemId() {
        return returnItemId;
    }

    public CSVBindingObject setReturnItemId(String returnItemId) {
        this.returnItemId = returnItemId;
        return this;
    }

    public String getPalletId() {
        return palletId;
    }

    public CSVBindingObject setPalletId(String palletId) {
        this.palletId = palletId;
        return this;
    }

    public String getDepartment() {
        return department;
    }

    public CSVBindingObject setDepartment(String department) {
        this.department = department;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public CSVBindingObject setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public CSVBindingObject setSubCategory(String subCategory) {
        this.subCategory = subCategory;
        return this;
    }

    public String getAsin() {
        return asin;
    }

    public CSVBindingObject setAsin(String asin) {
        this.asin = asin;
        return this;
    }

    public String getEan() {
        return ean;
    }

    public CSVBindingObject setEan(String ean) {
        this.ean = ean;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CSVBindingObject setDescription(String description) {
        this.description = description;
        return this;
    }

    public Long getQuantity() {
        return quantity;
    }

    public CSVBindingObject setQuantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public CSVBindingObject setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
        return this;
    }

    public Double getTotalRetail() {
        return totalRetail;
    }

    public CSVBindingObject setTotalRetail(Double totalRetail) {
        this.totalRetail = totalRetail;
        return this;
    }

    public String getLpn() {
        return lpn;
    }

    public CSVBindingObject setLpn(String lpn) {
        this.lpn = lpn;
        return this;
    }
}
