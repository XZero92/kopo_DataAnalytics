package kr.co.tlf.ex.jsp_final_ecommerce_ysh.dto;

import java.sql.Timestamp;

public class CategoryDTO {

    private int categoryNo; // NB_CATEGORY
    private int parentCategoryNo; // NB_PARENT_CATEGORY
    private String categoryName; // NM_CATEGORY
    private String fullCategoryName; // NM_FULL_CATEGORY
    private String explain; // NM_EXPLAIN
    private int level; // CN_LEVEL
    private int order; // CN_ORDER
    private String useYn; // YN_USE
    private String deleteYn; // YN_DELETE
    private String registerNo; // NO_REGISTER
    private Timestamp firstDate; // DA_FIRST_DATE

    // Getters and Setters
    public int getCategoryNo() {
        return categoryNo;
    }

    public void setCategoryNo(int categoryNo) {
        this.categoryNo = categoryNo;
    }

    public Integer getParentCategoryNo() {
        return parentCategoryNo;
    }

    public void setParentCategoryNo(Integer parentCategoryNo) {
        this.parentCategoryNo = parentCategoryNo;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getFullCategoryName() {
        return fullCategoryName;
    }

    public void setFullCategoryName(String fullCategoryName) {
        this.fullCategoryName = fullCategoryName;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getUseYn() {
        return useYn;
    }

    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    public String getDeleteYn() {
        return deleteYn;
    }

    public void setDeleteYn(String deleteYn) {
        this.deleteYn = deleteYn;
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