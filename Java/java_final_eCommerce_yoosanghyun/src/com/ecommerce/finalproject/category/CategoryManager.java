package com.ecommerce.finalproject.category;

import java.util.ArrayList;

public class CategoryManager {
    private ArrayList<CategoryData> categories;

    public CategoryManager() {
        categories = new ArrayList<>();
    }

    public void createCategory(CategoryData category) {
        categories.add(category);
    }

    public void createCategory(byte categoryID, byte categoryParentID, String categoryName, String categoryFullName, String categoryDescription, byte categoryLevel, byte categoryOrder) {
        CategoryData newCategory = new CategoryData();
        newCategory.setCategoryID(categoryID);
        newCategory.setCategoryParentID(categoryParentID);
        newCategory.setCategoryName(categoryName);
        newCategory.setCategoryFullName(categoryFullName);
        newCategory.setCategoryDescription(categoryDescription);
        newCategory.setCategoryLevel(categoryLevel);
        newCategory.setCategoryOrder(categoryOrder);

        categories.add(newCategory);
    }

    public void updateCategory(byte categoryID, CategoryData updatedCategory) {

    }

    public void changeCategoryStatus(byte categoryID, boolean isActive) {
        for (CategoryData category : categories) {
            if (category.getCategoryID() == categoryID) {
                category.setActive(isActive);
                break;
            }
        }
    }
    public void deleteCategory(byte categoryID) {

    }
}
