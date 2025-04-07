package com.ecommerce.finalproject.product;

import java.util.ArrayList;

public class ProductManager {
    ArrayList<ProductData> products;

    public ProductManager() {
        products = new ArrayList<>();
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
    }

    public boolean addProduct(String productName, String productDescription, String productStartDate, String productEndDate, int productPrice, int productSalePrice, int productQuantity, int deliveryFee) {
        ProductData newProduct = new ProductData();
        newProduct.setProductCode(productName);
        newProduct.setProductDescription(productDescription);
        newProduct.setProductStartDate(productStartDate);
        newProduct.setProductEndDate(productEndDate);
        newProduct.setProductPrice(productPrice);
        newProduct.setProductSalePrice(productSalePrice);
        newProduct.setProductStock(productQuantity);
        newProduct.setDeliveryFee(deliveryFee);

        return products.add(newProduct);
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
                return true;
            }
        }
        return false;
    }

    public boolean deleteProduct(String productCode) {
        for( ProductData product : products) {
            if (product.getProductCode().equals(productCode)) {
                products.remove(product);
                return true;
            }
        }
        return false;
    }

    public void updateProductStock(String productCode, int newStock) {
        for( ProductData product : products) {
            if (product.getProductCode().equals(productCode)) {
                product.setProductStock(newStock);
                break;
            }
        }
    }
}
