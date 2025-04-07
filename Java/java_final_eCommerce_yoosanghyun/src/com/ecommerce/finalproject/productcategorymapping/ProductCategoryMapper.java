package com.ecommerce.finalproject.productcategorymapping;

public class ProductCategoryMapper {
    private byte categoryID;
    private String productCode;
    private byte order;
    private String registeredUserID;
    private String creationDate;

    public ProductCategoryMapper(byte categoryID, String productCode, byte order) {
        this.categoryID = categoryID;
        this.productCode = productCode;
        this.order = order;
        this.creationDate = new java.util.Date().toString();
    }

    public byte getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(byte categoryID) {
        this.categoryID = categoryID;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public byte getOrder() {
        return order;
    }

    public void setOrder(byte order) {
        this.order = order;
    }

    public String getRegisteredUserID() {
        return registeredUserID;
    }

    public String getCreationDate() {
        return creationDate;
    }
}
