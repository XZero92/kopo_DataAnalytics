package com.example.jsp_21_1_ex1_memberex.model;

import java.sql.Time;
import java.time.LocalDate;
import java.sql.Timestamp;

public class MemberDTO {
    // Status 상수
    public static final String STATUS_INACTIVE = "ST00"; // 요청
    public static final String STATUS_ACTIVE = "ST01"; // 정상
    public static final String STATUS_DELETED = "ST02"; // 해지
    public static final String STATUS_SUSPENDED = "ST03"; // 일시정지

    // UserType 상수
    public static final String USER_TYPE_MEMBER = "10";
    public static final String USER_TYPE_ADMIN = "20";

    private String userId;      // ID_USER (이메일 형식)
    private String userName;    // NM_USER
    private String password;    // NM_PASWD
    private String encPassword; // NM_ENC_PASWD
    private String mobileNo;    // NO_MOBILE
    private String email;       // NM_EMAIL (userId와 동일)
    private String status;      // ST_STATUS
    private String userType;    // CD_USER_TYPE
    private String registerNo;  // NO_REGISTER
    private Timestamp firstDate; // DA_FIRST_DATE


    // 기본 생성자
    public MemberDTO() {}

    // 모든 필드를 포함한 생성자
    public MemberDTO(String userId, String userName, String password, String email, String mobileNo, String status, String userType, String registerNo, Timestamp firstDate) {
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

    public String getEncPassword() {
        return encPassword;
    }

    public void setEncPassword(String encPassword) {
        this.encPassword = encPassword;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Timestamp getFirstDate() {
        return firstDate;
    }

    public void setFirstDate(Timestamp firstDate) {
        this.firstDate = firstDate;
    }

}