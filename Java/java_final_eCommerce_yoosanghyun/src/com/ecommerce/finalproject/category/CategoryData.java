package com.ecommerce.finalproject.category;

import java.util.Date;

public class CategoryData {
    private byte categoryID;
    private byte categoryParentID;
    private String categoryName;
    private String categoryFullName;
    private String categoryDescription;
    private byte categoryLevel;
    private byte categoryOrder;
    private boolean isActive;
    private String registeredUserID;
    private Date creationDate;

    public CategoryData() {
        this.isActive = false;
        this.creationDate = new Date();
    }

    public CategoryData(byte categoryID, byte categoryParentID, String categoryName, String categoryFullName, String categoryDescription, byte categoryLevel, byte categoryOrder) {
        this.categoryID = categoryID;
        this.categoryParentID = categoryParentID;
        this.categoryName = categoryName;
        this.categoryFullName = categoryFullName;
        this.categoryDescription = categoryDescription;
        this.categoryLevel = categoryLevel;
        this.categoryOrder = categoryOrder;
        this.isActive = false;
        this.creationDate = new Date();
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getRegisteredUserID() {
        return registeredUserID;
    }

    public void setRegisteredUserID(String registeredUserID) {
        this.registeredUserID = registeredUserID;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public byte getCategoryOrder() {
        return categoryOrder;
    }

    public void setCategoryOrder(byte categoryOrder) {
        this.categoryOrder = categoryOrder;
    }

    public byte getCategoryLevel() {
        return categoryLevel;
    }

    public void setCategoryLevel(byte categoryLevel) {
        this.categoryLevel = categoryLevel;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public String getCategoryFullName() {
        return categoryFullName;
    }

    public void setCategoryFullName(String categoryFullName) {
        this.categoryFullName = categoryFullName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public byte getCategoryParentID() {
        return categoryParentID;
    }

    public void setCategoryParentID(byte categoryParentID) {
        this.categoryParentID = categoryParentID;
    }

    public byte getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(byte categoryID) {
        this.categoryID = categoryID;
    }
}
