package com.ecommerce.finalproject.user;

import java.security.PublicKey;
import java.util.ArrayList;

public class UserManager {
    private ArrayList<UserData> userList;
    private UserData loggedInUser;

    public static final String PW_INVAL_LENGTH = "E_PW_LENGTH";
    public static final String PW_INVAL_CHAR = "E_PW_CHAR";

    public static boolean checkIDValiation(String id) {
        return id.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
    }
    public static String checkPWValidation(String password) {
        if (password.length() < 5 && password.length() > 15)
            return "E_PW_LENGTH";
        if (password.matches(".*[a-zA-Z].*") && password.matches(".*[0-9].*"))
            return "E_PW_CHAR";

        return password;
    }

    public UserManager() {
        userList = new ArrayList<>();
        loggedInUser = null;
    }

    public boolean registerUser(UserData user) {
        if (userList.isEmpty()) {
            user.setUserType(UserData.USER_ADMIN);
        }

        return userList.add(user);
    }

    public boolean updateUser(String userID, UserData updatedUser) {
        for( UserData user : userList) {
            if (user.getUserID().equals(userID)) {
                user.setUserName(updatedUser.getUserName());
                user.setUserPassword(updatedUser.getUserPassword());
                user.setUserMobileNumber(updatedUser.getUserMobileNumber());
                user.setUserEmail(updatedUser.getUserEmail());
                return true;
            }
        }
        return false;
    }

    public boolean changeUserPassword(String userID, String newPassword) {
        for( UserData user : userList) {
            if (user.getUserID().equals(userID)) {
                user.setUserPassword(newPassword);
                return true;
            }
        }
        return false;
    }

    public boolean deleteUser(String userID) {
        for( UserData user : userList) {
            if (user.getUserID().equals(userID)) {
                user.setUserStatus(UserData.STATUS_DELETED);
                return true;
            }
        }
        return false;
    }

    public boolean loginUser(String userID, String userPassword) {
        for( UserData user : userList) {
            if (user.getUserID().equals(userID) && user.getUserPassword().equals(userPassword)) {
                loggedInUser = user;
                return true;
            }
        }
        return false;
    }

    public boolean logoutUser() {
        loggedInUser = null;

        return true;
    }

    public UserData getLoggedInUser() {
        return loggedInUser;
    }
}
