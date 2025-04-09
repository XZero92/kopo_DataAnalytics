package com.ecommerce.finalproject;

import com.ecommerce.finalproject.user.*;
import com.ecommerce.finalproject.product.*;
import com.ecommerce.finalproject.display.*;
import com.ecommerce.finalproject.util.*;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UserManager userManager = UserManager.getInstance();
        UserEx userEx = new UserEx();

        while(true) {
            if(userManager.getLoggedInUser() == null) {
                userEx.showMenu();
                userEx.selectMenu();
            }
            else {
                System.out.println("환영합니다, " + userManager.getLoggedInUser().getUserName() + "님!");
                System.out.println("======================================================");
                System.out.println("| 1. 회원정보 관리 | 2. 상품 열람 | 3. 상품 관리 | 0. 종료 |");
                System.out.println("======================================================");
                System.out.print("메뉴를 선택하세요> ");
                String menuIdx = sc.nextLine();
                switch(menuIdx) {
                    case "1":
                        userEx.run();
                        break;
                    case "2":
                        DisplayProducts displayProducts = new DisplayProducts();
                        displayProducts.run();
                        break;
                    case "3":
                        if(userManager.getLoggedInUser().getUserType().equals(UserData.USER_COMMON)) {
                            System.out.println("상품 관리 권한이 없습니다.");
                            break;
                        } else {
                            ProductEx productEx = new ProductEx();
                            productEx.run();
                            break;
                        }
                    case "0":
                        System.out.println("프로그램을 종료합니다.");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("해당하는 메뉴가 없습니다.");
                }

            }
        }


    }
}
