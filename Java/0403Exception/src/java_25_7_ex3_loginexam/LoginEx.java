package java_25_7_ex3_loginexam;

import java.util.Scanner;

public class LoginEx {
    Account a;

    public void login(String id, String pw) throws NotExistIDException, WrongPasswordException {
        if (!a.id.equals(id)) {
            throw new NotExistIDException("ID가 존재하지 않습니다.");
        } else if (!a.password.equals(pw)) {
            throw new WrongPasswordException("잘못된 패스워드 입니다.");
        } else {
            System.out.println("로그인 성공");
        }
    }

    public static void main(String[] args) {
        LoginEx l = new LoginEx();
        l.a = new Account("batman", "12345");

        Scanner sc = new Scanner(System.in);

        System.out.print("ID 입력: ");
        String id = sc.nextLine();
        System.out.print("PW 입력: ");
        String pw = sc.nextLine();

        try {
            l.login(id, pw);
        } catch (NotExistIDException | WrongPasswordException e) {
            System.out.println(e.getMessage());
        }
    }
}
