package kr.co.tlf.ex.jsp_final_ecommerce_ysh.dto;

import java.sql.Timestamp;

public class BasketItemDTO {
    private int basketItemId; // NB_BASKET_ITEM
    private int basketId; // NB_BASKET
    private int itemOrder; // CN_BASKET_ITEM_ORDER
    private String productNo; // NO_PRODUCT
    private String userId; // ID_USER
    private int itemPrice; // QT_BASKET_ITEM_PRICE
    private int itemQuantity; // QT_BASKET_ITEM
    private int itemAmount; // QT_BASKET_ITEM_AMOUNT
    private String registerNo; // NO_REGISTER
    private Timestamp firstDate; // DA_FIRST_DATE

    // Getters and Setters
    public int getBasketItemId() {
        return basketItemId;
    }

    public void setBasketItemId(int basketItemId) {
        this.basketItemId = basketItemId;
    }

    public int getBasketId() {
        return basketId;
    }

    public void setBasketId(int basketId) {
        this.basketId = basketId;
    }

    public int getItemOrder() {
        return itemOrder;
    }

    public void setItemOrder(int itemOrder) {
        this.itemOrder = itemOrder;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public int getItemAmount() {
        return itemAmount;
    }

    public void setItemAmount(int itemAmount) {
        this.itemAmount = itemAmount;
    }

    public String getRegisterNo() {
        return registerNo;
    }

    public void setRegisterNo(String registerNo) {
        this.registerNo = registerNo;
    }

    public Timestamp getFirstDate() {
        return firstDate;
    }

    public void setFirstDate(Timestamp firstDate) {
        this.firstDate = firstDate;
    }
}