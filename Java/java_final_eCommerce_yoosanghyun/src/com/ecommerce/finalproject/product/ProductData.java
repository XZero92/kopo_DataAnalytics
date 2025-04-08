package com.ecommerce.finalproject.product;

import com.ecommerce.finalproject.util.DateUtils;

import java.util.Date;

public class ProductData {
    private String productCode;
    private String productName;
    private String productDescription;
    private String productContentsID;
    private Date productStartDate;
    private Date productEndDate;
    private int productPrice;
    private int productSalePrice;
    private int productStock;
    private int deliveryFee;
    private String registeredUserID;
    private Date creationDate;

    public ProductData() {
        this.creationDate = new Date();
    }

    public ProductData(ProductData data) {
        this.productCode = data.productCode;
        this.productName = data.productName;
        this.productDescription = data.productDescription;
        this.productContentsID = data.productContentsID;
        this.productStartDate = data.productStartDate;
        this.productEndDate = data.productEndDate;
        this.productPrice = data.productPrice;
        this.productSalePrice = data.productSalePrice;
        this.productStock = data.productStock;
        this.deliveryFee = data.deliveryFee;
        this.registeredUserID = data.registeredUserID;
        this.creationDate = data.creationDate;
    }

    public ProductData(String productCode, String productName, String productDescription, String productContentsID, Date productStartDate, Date productEndDate, int productPrice, int productSalePrice, int productStock, int deliveryFee, Date creationDate) {
        this.productCode = productCode;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productContentsID = productContentsID;
        this.productStartDate = productStartDate;
        this.productEndDate = productEndDate;
        this.productPrice = productPrice;
        this.productSalePrice = productSalePrice;
        this.productStock = productStock;
        this.deliveryFee = deliveryFee;
        this.creationDate = creationDate;
    }

    public String getProductContentsID() {
        return productContentsID;
    }

    public void setProductContentsID(String productContentsID) {
        this.productContentsID = productContentsID;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Date getProductStartDate() {
        return productStartDate;
    }
    public void setProductStartDate(Date productStartDate) {
        this.productStartDate = productStartDate;
    }

    public void setProductStartDate(String productStartDate) {
        this.productStartDate = DateUtils.parseDate(productStartDate);
    }

    public Date getProductEndDate() {
        return productEndDate;
    }

    public void setProductEndDate(Date productEndDate) {
        this.productEndDate = productEndDate;
    }

    public void setProductEndDate(String productEndDate) {
        this.productEndDate = DateUtils.parseDate(productEndDate);
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductSalePrice() {
        return productSalePrice;
    }

    public void setProductSalePrice(int productSalePrice) {
        this.productSalePrice = productSalePrice;
    }

    public int getProductStock() {
        return productStock;
    }

    public void setProductStock(int productStock) {
        this.productStock = productStock;
    }

    public int getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(int deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public String getRegisteredUserID() {
        return registeredUserID;
    }

    public Date getCreationDate() {
        return creationDate;
    }
}
