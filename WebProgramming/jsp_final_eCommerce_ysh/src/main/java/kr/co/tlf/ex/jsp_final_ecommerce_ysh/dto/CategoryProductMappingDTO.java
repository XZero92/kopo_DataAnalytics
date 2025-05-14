package kr.co.tlf.ex.jsp_final_ecommerce_ysh.dto;

import java.sql.Timestamp;

public class CategoryProductMappingDTO {
    // TB_CATEGORY_PRODUCT_MAPPING
    private int categoryNo; // NB_CATEGORY
    private String productNo; // NO_PRODUCT
    private int order; // CN_ORDER
    private String registerNo; // NO_REGISTER
    private Timestamp firstDate; // DA_FIRST_DATE

    // Getters and Setters
    public int getCategoryNo() {
        return categoryNo;
    }

    public void setCategoryNo(int categoryNo) {
        this.categoryNo = categoryNo;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
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