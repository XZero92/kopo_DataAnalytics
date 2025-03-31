package java_09_2_ex1_accountManager;

import java.util.Scanner;

public class BankApplication {
    private Account[] accounts = new Account[100];

    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        BankApplication app = new BankApplication();
        app.run();
    }

    public void run() {
        programLoop:
        while (true) {
            System.out.println("-------------------------------------------------------");
            System.out.println("1.계좌 생성 | 2.계좌 목록 | 3.예금 | 4.출금 | 5.종료");
            System.out.println("-------------------------------------------------------");
            System.out.print("선택 > ");

            String menuIdx = sc.nextLine();

            switch (menuIdx) {
                case "1":
                    addAccount();
                    break;
                case "2":
                    showAccounts();
                    break;
                case "3":
                    deposit();
                    break;
                case "4":
                    withdraw();
                    break;
                case "5":
                    System.out.println("프로그램을 종료합니다.");
                    break programLoop;
                default:
                    System.out.println("잘못된 선택입니다.");
            }
        }
    }

    public void addAccount() {
        int currentIdx;
        for (currentIdx = 0; currentIdx < accounts.length; currentIdx++) {
            if (accounts[currentIdx] == null) {

                System.out.println("---------");
                System.out.println("계좌 생성");
                System.out.println("---------");
                System.out.print("계좌번호: ");
                String accountNumber = sc.next();
                System.out.print("예금주: ");
                String accountHolder = sc.next();
                System.out.print("초기입금액: ");
                int initialDeposit = sc.nextInt();

                accounts[currentIdx] = new Account(accountNumber, accountHolder, initialDeposit);
                System.out.println("계좌가 생성되었습니다.");
                return;
            }
        }
        System.out.println("목록이 가득 찼습니다.");
    }

    public void showAccounts() {
        System.out.println("---------");
        System.out.println("계좌 목록");
        System.out.println("---------");
        for (Account account : accounts) {
            if (account != null) {
                System.out.println(account.getAccountNumber() + " "
                        + account.getAccountHolder() + "\t" + account.getBalance());
            }
        }
    }

    public void deposit() {
        System.out.println("---------");
        System.out.println("예금");
        System.out.println("---------");
        System.out.print("계좌번호: ");
        String accountNumber = sc.next();
        System.out.print("예금액: ");
        int amount = Integer.parseInt(sc.nextLine());

        for (Account account : accounts) {
            if (account != null && account.getAccountNumber().equals(accountNumber)) {
                account.deposit(amount);
                return;
            }
        }
        System.out.println("계좌를 찾을 수 없습니다.");
    }

    public void withdraw() {
        System.out.println("---------");
        System.out.println("출금");
        System.out.println("---------");
        System.out.print("계좌번호: ");
        String accountNumber = sc.next();
        System.out.print("출금액: ");
        int amount = Integer.parseInt(sc.nextLine());

        for (Account account : accounts) {
            if (account != null && account.getAccountNumber().equals(accountNumber)) {
                account.withdraw(amount);
                return;
            }
        }
        System.out.println("계좌를 찾을 수 없습니다.");
    }
}
