package kr.co.tlf.ex.jsp_final_ecommerce_ysh.dto;

import java.sql.Timestamp;

public class OrderDTO {
    // 주문 구분 상수
    public static final String ORDER_TYPE_ORDER = "10"; // 일반 주문
    // 주문 상태 상수
    public static final String ORDER_STATUS_DONE = "10"; // 주문 완료
    // 결제 상태 상수
    public static final String PAYMENT_STATUS_DONE = "20"; // 결제 완료

    // TB_ORDER
    private String orderId; // ID_ORDER
    private String userId; // ID_USER
    private int orderAmount; // QT_ORDER_AMOUNT
    private int deliveryFee; // QT_DELI_MONEY
    private int deliveryPeriod; // QT_DELI_PERIOD
    private String orderPersonName; // NM_ORDER_PERSON
    private String receiverName; // NM_RECEIVER
    private String deliveryZipNo; // NO_DELIVERY_ZIPNO
    private String deliveryAddress; // NM_DELIVERY_ADDRESS
    private String receiverTelNo; // NM_RECEIVER_TELNO
    private String deliverySpace; // NM_DELIVERY_SPACE
    private String orderType; // CD_ORDER_TYPE
    private Timestamp orderDate; // DA_ORDER
    private String orderStatus; // ST_ORDER
    private String paymentStatus; // ST_PAYMENT
    private String registerNo; // NO_REGISTER
    private Timestamp firstDate; // DA_FIRST_DATE

    // Getters and Setters
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(int orderAmount) {
        this.orderAmount = orderAmount;
    }

    public int getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(int deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public int getDeliveryPeriod() {
        return deliveryPeriod;
    }

    public void setDeliveryPeriod(int deliveryPeriod) {
        this.deliveryPeriod = deliveryPeriod;
    }

    public String getOrderPersonName() {
        return orderPersonName;
    }

    public void setOrderPersonName(String orderPersonName) {
        this.orderPersonName = orderPersonName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getDeliveryZipNo() {
        return deliveryZipNo;
    }

    public void setDeliveryZipNo(String deliveryZipNo) {
        this.deliveryZipNo = deliveryZipNo;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getReceiverTelNo() {
        return receiverTelNo;
    }

    public void setReceiverTelNo(String receiverTelNo) {
        this.receiverTelNo = receiverTelNo;
    }

    public String getDeliverySpace() {
        return deliverySpace;
    }

    public void setDeliverySpace(String deliverySpace) {
        this.deliverySpace = deliverySpace;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
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