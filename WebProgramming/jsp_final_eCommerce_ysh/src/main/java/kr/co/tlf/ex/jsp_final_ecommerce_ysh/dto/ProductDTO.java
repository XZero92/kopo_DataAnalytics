package kr.co.tlf.ex.jsp_final_ecommerce_ysh.dto;

import java.sql.Timestamp;

public class ProductDTO {
    // TB_PRODUCT
    private String productNo;   // NO_PRODUCT
    private String productName; // NM_PRODUCT
    private String detailExplain; // NM_DETAIL_EXPLAIN
    private String fileId; // ID_FILE
    private String startDate; // DT_START_DATE
    private String endDate; // DT_END_DATE
    private int customerQuantity; // QT_CUSTOMER
    private int salePrice;  // QT_SALE_PRICE
    private int stockQuantity;  // QT_STOCK
    private int deliveryFee; // QT_DELIVERY_FEE
    private String registerNo;  // NO_REGISTER
    private Timestamp firstDate;    // DA_FIRST_DATE

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDetailExplain() {
        return detailExplain;
    }

    public void setDetailExplain(String detailExplain) {
        this.detailExplain = detailExplain;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getCustomerQuantity() {
        return customerQuantity;
    }

    public void setCustomerQuantity(int customerQuantity) {
        this.customerQuantity = customerQuantity;
    }

    public int getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(int salePrice) {
        this.salePrice = salePrice;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public int getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(int deliveryFee) {
        this.deliveryFee = deliveryFee;
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
