package com.ecommerce.finalproject.user;

import java.util.Date;

public class UserData {
    public static final String STATUS_REQUESTED = "ST00";
    public static final String STATUS_NORMAL = "ST01";
    public static final String STATUS_DELETED = "ST02";
    public static final String USER_COMMON= "10";
    public static final String USER_ADMIN = "20";

    private String userID;
    private String userName;
    private String userPassword;
    private String userMobileNumber;
    private String userEmail;
    private String userStatus;
    private String userType;
    private final Date creationDate;

    public UserData() {
        this.userStatus = STATUS_NORMAL;
        this.creationDate = new Date();
    }

    public UserData(String userID, String userName, String userPassword, String userMobileNumber, String userEmail, String userType) {
        this.userID = userID;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userMobileNumber = userMobileNumber;
        this.userEmail = userEmail;
        this.userStatus = STATUS_NORMAL;
        this.userType = userType;
        this.creationDate = new Date();
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserMobileNumber() {
        return userMobileNumber;
    }

    public void setUserMobileNumber(String userMobileNumber) {
        this.userMobileNumber = userMobileNumber;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Date getCreationDate() {
        return creationDate;
    }
}
