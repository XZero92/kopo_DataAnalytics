package com.example.jsp_21_1_ex1_memberex.model;

import java.time.LocalDate;

public class MemberDTO {
    // Status 상수
    public static final String STATUS_INACTIVE = "ST00"; // 요청
    public static final String STATUS_ACTIVE = "ST01"; // 정상
    public static final String STATUS_DELETED = "ST02"; // 해지
    public static final String STATUS_SUSPENDED = "ST03"; // 일시정지

    // UserType 상수
    public static final String USER_TYPE_MEMBER = "10";
    public static final String USER_TYPE_ADMIN = "20";

    private String userId;
    private String userName;
    private String password;
    private String email;
    private String mobileNo;
    private String status;
    private String userType;
    private String registerNo;
    private LocalDate firstDate;

    // 기본 생성자
    public MemberDTO() {}

    // 모든 필드를 포함한 생성자
    public MemberDTO(String userId, String userName, String password, String email, String mobileNo, String status, String userType, String registerNo, LocalDate firstDate) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.mobileNo = mobileNo;
        this.status = status;
        this.userType = userType;
        this.registerNo = registerNo;
        this.firstDate = firstDate;
    }

    // Getter와 Setter
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getMobileNo() {
        return mobileNo;
    }
    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getUserType() {
        return userType;
    }
    public void setUserType(String userType) {
        this.userType = userType;
    }
    public String getRegisterNo() {
        return registerNo;
    }
    public void setRegisterNo(String registerNo) {
        this.registerNo = registerNo;
    }
    public LocalDate getFirstDate() {
        return firstDate;
    }
    public void setFirstDate(LocalDate firstDate) {
        this.firstDate = firstDate;
    }
}