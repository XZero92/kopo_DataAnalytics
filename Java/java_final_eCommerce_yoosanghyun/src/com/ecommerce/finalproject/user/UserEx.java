package com.ecommerce.finalproject.user;

import java.util.Scanner;

public class UserEx {
    Scanner sc = new Scanner(System.in);
    UserManager userManager = UserManager.getInstance();

    public void showMenu() {
        if(userManager.getLoggedInUser() != null) {
            System.out.println("============================================================");
            System.out.println("| 1. 로그아웃 | 2. 회원정보 수정 | 3. 비밀번호 변경 | 4. 탈퇴 요청 |");
            System.out.println("============================================================");
        } else {
            System.out.println("=================================");
            System.out.println("| 1. 로그인 | 2. 회원가입 | 0. 종료 |");
            System.out.println("=================================");
        }
        System.out.print("메뉴를 선택하세요: ");
    }

    public void selectMenu() {
        String menuIdx = sc.nextLine();

        if(userManager.getLoggedInUser() != null) {
            switch (menuIdx) {
                case "1":
                    userManager.logoutUser();
                    System.out.println("로그아웃 되었습니다.");
                    break;
                case "2":
                    updateUser();
                    break;
                default:
                    System.out.println("해당하는 메뉴가 없습니다.");
            }
        } else {
            switch(menuIdx) {
                case "1":
                    loginUser();
                    break;
                case "2":
                    registerUser();
                    break;
                case "0":
                    System.out.println("프로그램을 종료합니다.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("해당하는 메뉴가 없습니다.");
            }
        }
    }

    public void loginUser() {
        System.out.println("------");
        System.out.println("로그인");
        System.out.println("------");

        String id;
        while (true) {
            System.out.print("ID(email): ");
            id = sc.nextLine();
            if (id.isEmpty()) {
                System.out.println("ID(이메일 주소)를 입력하세요.");
                continue;
            }
            break;
        }

        String password;
        while (true) {
            System.out.print("비밀번호: ");
            password = sc.nextLine();
            if (password.isEmpty()) {
                System.out.println("비밀번호를 입력하세요.");
                continue;
            }
            break;
        }

        userManager.loginUser(id, password);
    }

    public void registerUser() {
        System.out.println("-------");
        System.out.println("회원가입");
        System.out.println("-------");

        String id;
        while (true) {
            System.out.print("ID(email): ");
            id = sc.nextLine();
            if (id.isEmpty()) {
                System.out.println("ID(이메일 주소)를 입력하세요.");
                continue;
            }
            if (!UserManager.checkIDValiation(id)) {
                System.out.println("잘못된 이메일 주소입니다.");
                continue;
            }
            break;
        }

        String password;
        while (true) {
            System.out.print("비밀번호: ");
            password = sc.nextLine();
            if (password.isEmpty()) {
                System.out.println("비밀번호를 입력하세요.");
                continue;
            }
            switch (UserManager.checkPWValidation(password)) {
                case UserManager.PW_INVALID_LENGTH:
                    System.out.println("비밀번호는 5자 이상, 15자 이하로 입력해야 합니다.");
                    continue;
                case UserManager.PW_INVALID_CHAR:
                    System.out.println("비밀번호에는 숫자, 알파벳 대문자, 소문자가 각각 하나 이상이 포함되어야합니다.");
                    continue;
                default:
                    break;
            }
            break;
        }

        System.out.print("이름: ");
        String name = sc.nextLine();

        System.out.print("전화번호: ");
        String mobileNumber = sc.nextLine();

        UserData newUser = new UserData();
        newUser.setUserID(id);
        newUser.setUserPassword(password);
        newUser.setUserName(name);
        newUser.setUserMobileNumber(mobileNumber);
        newUser.setUserEmail(id);

        userManager.registerUser(newUser);
    }

    public void updateUser() {
        System.out.println("-----------");
        System.out.println("회원정보 수정");
        System.out.println("-----------");

        String id = userManager.getLoggedInUser().getUserID();

        System.out.print("이름: ");
        String name = sc.nextLine();

        System.out.print("전화번호: ");
        String mobileNumber = sc.nextLine();

        UserData updatedUser = new UserData();
        updatedUser.setUserName(name);
        updatedUser.setUserMobileNumber(mobileNumber);
        userManager.updateUser(id, updatedUser);
    }

    public void changePassword() {
        System.out.println("-----------");
        System.out.println("비밀번호 변경");
        System.out.println("-----------");

        String id = userManager.getLoggedInUser().getUserID();

        String newPassword;
        while (true) {
            System.out.print("새 비밀번호: ");
            newPassword = sc.nextLine();
            if (newPassword.isEmpty()) {
                System.out.println("비밀번호를 입력하세요.");
                continue;
            }
            if (newPassword.length() < 5 || newPassword.length() > 15) {
                System.out.println("비밀번호는 5자 이상, 15자 이하로 입력해야 합니다.");
                continue;
            }
            if (!newPassword.matches(".*[a-z].*") || !newPassword.matches(".*[A-Z].*") || !newPassword.matches(".*[0-9].*")) {
                System.out.println("비밀번호에는 숫자, 알파벳 대문자, 소문자가 각각 하나 이상이 포함되어야합니다.");
                continue;
            }
            if(newPassword.equals(userManager.getLoggedInUser().getUserPassword())) {
                System.out.println("새 비밀번호는 기존 비밀번호와 달라야 합니다.");
                continue;
            }
            break;
        }

        userManager.changeUserPassword(id, newPassword);
    }
}
