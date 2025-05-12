package kr.co.tlf.ex.jsp_final_ecommerce_ysh.dto;

import java.sql.Timestamp;

public class BasketDTO {
    private int basketId; // NB_BASKET
    private String userId; // ID_USER
    private int basketAmount; // QT_BASKET_AMOUNT
    private String registerNo; // NO_REGISTER
    private Timestamp firstDate; // DA_FIRST_DATE

    // Getters and Setters
    public int getBasketId() {
        return basketId;
    }

    public void setBasketId(int basketId) {
        this.basketId = basketId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getBasketAmount() {
        return basketAmount;
    }

    public void setBasketAmount(int basketAmount) {
        this.basketAmount = basketAmount;
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