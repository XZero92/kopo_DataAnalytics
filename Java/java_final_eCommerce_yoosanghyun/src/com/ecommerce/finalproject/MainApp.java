package com.ecommerce.finalproject;

import com.ecommerce.finalproject.user.UserEx;
import com.ecommerce.finalproject.user.UserManager;

public class MainApp {
    public static void main(String[] args) {
        UserManager userManager = UserManager.getInstance();
        UserEx userEx = new UserEx();

        while(true) {
            userEx.showMenu();
            userEx.selectMenu();


        }


    }
}
