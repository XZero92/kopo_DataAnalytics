package com.ecommerce.finalproject.product;

import com.ecommerce.finalproject.util.JsonUtils;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.ArrayList;

public class ProductManager {
    private static final String PRODUCT_DB_PATH = "src/com/ecommerce/finalproject/data/productdb.json";
    ArrayList<ProductData> products;

    public ProductManager() {
        loadProductsFromFile();
    }

    public ArrayList<ProductData> getProducts() {
        return products;
    }

    public ProductData getProductById(String productId) {
        for (ProductData product : products) {
            if (product.getProductCode().equals(productId)) {
                return product;
            }
        }
        return null;
    }

    public void addProduct(ProductData product) {
        products.add(product);
        saveProductsToFile();
    }

    public void addProduct(String productName, String productDescription, String productStartDate, String productEndDate, int productPrice, int productSalePrice, int productQuantity, int deliveryFee) {
        ProductData newProduct = new ProductData();
        newProduct.setProductCode(productName);
        newProduct.setProductDescription(productDescription);
        newProduct.setProductStartDate(productStartDate);
        newProduct.setProductEndDate(productEndDate);
        newProduct.setProductPrice(productPrice);
        newProduct.setProductSalePrice(productSalePrice);
        newProduct.setProductStock(productQuantity);
        newProduct.setDeliveryFee(deliveryFee);

        products.add(newProduct);
        saveProductsToFile();
    }

    public boolean updateProduct(String productCode, ProductData updatedProduct) {
        for( ProductData product : products) {
            if (product.getProductCode().equals(productCode)) {
                product.setProductName(updatedProduct.getProductName());
                product.setProductDescription(updatedProduct.getProductDescription());
                product.setProductStartDate(updatedProduct.getProductStartDate());
                product.setProductEndDate(updatedProduct.getProductEndDate());
                product.setProductPrice(updatedProduct.getProductPrice());
                product.setProductSalePrice(updatedProduct.getProductSalePrice());
                product.setProductStock(updatedProduct.getProductStock());
                product.setDeliveryFee(updatedProduct.getDeliveryFee());
                saveProductsToFile();
                return true;
            }
        }
        return false;
    }

    public boolean updateProduct(String productCode, String productName, String productDescription, String productStartDate, String productEndDate, int productPrice, int productSalePrice, int productQuantity, int deliveryFee) {
        for( ProductData product : products) {
            if (product.getProductCode().equals(productCode)) {
                product.setProductName(productName);
                product.setProductDescription(productDescription);
                product.setProductStartDate(productStartDate);
                product.setProductEndDate(productEndDate);
                product.setProductPrice(productPrice);
                product.setProductSalePrice(productSalePrice);
                product.setProductStock(productQuantity);
                product.setDeliveryFee(deliveryFee);
                saveProductsToFile();
                return true;
            }
        }
        return false;
    }

    public boolean deleteProduct(String productCode) {
        for( ProductData product : products) {
            if (product.getProductCode().equals(productCode)) {
                products.remove(product);
                saveProductsToFile();
                return true;
            }
        }
        return false;
    }

    public void updateProductStock(String productCode, int newStock) {
        for( ProductData product : products) {
            if (product.getProductCode().equals(productCode)) {
                product.setProductStock(newStock);
                saveProductsToFile();
                break;
            }
        }
    }

    private void loadProductsFromFile() {
        Type productListType = new TypeToken<ArrayList<ProductData>>() {}.getType();
        List<ProductData> loadedProducts = JsonUtils.readListFromFile(PRODUCT_DB_PATH, productListType);
        if (loadedProducts != null) {
            products = new ArrayList<>(loadedProducts); // 명시적으로 ArrayList로 변환
        }
    }

    private void saveProductsToFile() {
        JsonUtils.writeListToFile(PRODUCT_DB_PATH, products);
    }
}
