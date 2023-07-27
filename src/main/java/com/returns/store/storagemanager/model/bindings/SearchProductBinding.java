package com.returns.store.storagemanager.model.bindings;

public class SearchProductBinding {

    private String returnItemId;
    private String asin;
    private String category;
    private String condition;
    private String department;
    private String ean;
    private String lpn;
    private String palletId;

    public SearchProductBinding() {
    }

    public String getReturnItemId() {
        return returnItemId;
    }

    public SearchProductBinding setReturnItemId(String returnItemId) {
        this.returnItemId = returnItemId;
        return this;
    }

    public String getAsin() {
        return asin;
    }

    public SearchProductBinding setAsin(String asin) {
        this.asin = asin;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public SearchProductBinding setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getCondition() {
        return condition;
    }

    public SearchProductBinding setCondition(String condition) {
        this.condition = condition;
        return this;
    }

    public String getDepartment() {
        return department;
    }

    public SearchProductBinding setDepartment(String department) {
        this.department = department;
        return this;
    }

    public String getEan() {
        return ean;
    }

    public SearchProductBinding setEan(String ean) {
        this.ean = ean;
        return this;
    }

    public String getLpn() {
        return lpn;
    }

    public SearchProductBinding setLpn(String lpn) {
        this.lpn = lpn;
        return this;
    }

    public String getPalletId() {
        return palletId;
    }

    public SearchProductBinding setPalletId(String palletId) {
        this.palletId = palletId;
        return this;
    }

    public boolean isEmpty() {
        return (returnItemId == null || returnItemId.trim().isEmpty()) &&
                (asin == null || asin.trim().isEmpty()) &&
                (category == null || category.trim().isEmpty()) &&
                (condition == null || condition.trim().isEmpty()) &&
                (department == null || department.trim().isEmpty()) &&
                (ean == null || ean.trim().isEmpty()) &&
                (lpn == null || lpn.trim().isEmpty()) &&
                (palletId == null || palletId.trim().isEmpty());
    }


    @Override
    public String toString() {
        return "SearchProductBinding{" +
                "returnItemId='" + returnItemId + '\'' +
                ", asin='" + asin + '\'' +
                ", category='" + category + '\'' +
                ", condition='" + condition + '\'' +
                ", department='" + department + '\'' +
                ", ean='" + ean + '\'' +
                ", lpn='" + lpn + '\'' +
                ", palletId='" + palletId + '\'' +
                '}';
    }
}
