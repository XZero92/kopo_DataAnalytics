package kr.co.tlf.ex.jsp_final_ecommerce_ysh.dto;

import java.sql.Timestamp;

public class OrderItemDTO {
    // TB_ORDER_ITEM 컬럼 매핑
    private String orderItemId;            // ID_ORDER_ITEM
    private String orderId;                // ID_ORDER
    private int orderItemNo;               // CN_ORDER_ITEM
    private String productNo;              // NO_PRODUCT
    private String userId;                 // ID_USER
    private int unitPrice;                 // QT_UNIT_PRICE
    private int orderItemQuantity;         // QT_ORDER_ITEM
    private Integer orderItemAmount;       // QT_ORDER_ITEM_AMOUNT
    private int deliveryFee;               // QT_ORDER_ITEM_DELIVERY_FEE
    private String paymentStatus;          // ST_PAYMENT
    private String registerNo;             // NO_REGISTER
    private Timestamp firstDate;           // DA_FIRST_DATE

    // Getters and Setters
    public String getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(String orderItemId) {
        this.orderItemId = orderItemId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getOrderItemNo() {
        return orderItemNo;
    }

    public void setOrderItemNo(int orderItemNo) {
        this.orderItemNo = orderItemNo;
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

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getOrderItemQuantity() {
        return orderItemQuantity;
    }

    public void setOrderItemQuantity(int orderItemQuantity) {
        this.orderItemQuantity = orderItemQuantity;
    }

    public Integer getOrderItemAmount() {
        return orderItemAmount;
    }

    public void setOrderItemAmount(Integer orderItemAmount) {
        this.orderItemAmount = orderItemAmount;
    }

    public int getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(int deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
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