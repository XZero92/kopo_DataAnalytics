package com.ecommerce.finalproject.user;

import com.ecommerce.finalproject.util.JsonUtils;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.ArrayList;

public class UserManager {
    private static final String USER_DB_FILE = "src/com/ecommerce/finalproject/data/userdb.json";
    public static final String PW_INVALID_LENGTH = "E_PW_LENGTH";
    public static final String PW_INVALID_CHAR = "E_PW_CHAR";

    private static UserManager instance = new UserManager();
    private ArrayList<UserData> userList;
    private UserData loggedInUser;

    public static boolean checkIDValiation(String id) {
        return id.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
    }
    public static String checkPWValidation(String password) {
        if (password.length() < 5 && password.length() > 15)
            return "E_PW_LENGTH";
        if (!password.matches(".*[a-z].*") || !password.matches(".*[A-Z].*") || !password.matches(".*[0-9].*"))
            return "E_PW_CHAR";

        return password;
    }

    public UserManager() {
        loadUsers();
        loggedInUser = null;
    }

    public static UserManager getInstance() {
        return instance;
    }

    public boolean registerUser(UserData user) {
        if (userList.isEmpty()) {
            user.setUserType(UserData.USER_ADMIN);
        }
        boolean added = userList.add(user);
        if (added) {
            saveUsers();
        }
        return added;
    }

    public boolean updateUser(String userID, UserData updatedUser) {
        for( UserData user : userList) {
            if (user.getUserID().equals(userID)) {
                user.setUserName(updatedUser.getUserName());
                user.setUserPassword(updatedUser.getUserPassword());
                user.setUserMobileNumber(updatedUser.getUserMobileNumber());
                user.setUserEmail(updatedUser.getUserEmail());
                saveUsers();
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
                saveUsers();
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

    private void saveUsers() {
        JsonUtils.writeListToFile(USER_DB_FILE, userList);
    }

    private void loadUsers() {
        Type userListType = new TypeToken<ArrayList<UserData>>() {}.getType();
        List<UserData> loadedUsers = JsonUtils.readListFromFile(USER_DB_FILE, userListType);
        if (loadedUsers != null) {
            userList = new ArrayList<>(loadedUsers);
        }
    }
}
