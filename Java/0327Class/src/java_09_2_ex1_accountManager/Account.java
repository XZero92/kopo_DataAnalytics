package java_09_2_ex1_accountManager;

public class Account {
    private String accountNumber;
    private String accountHolder;
    private int balance;

    public Account(String accountNumber, String accountHolder, int balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public int getBalance() {
        return balance;
    }

    public void deposit(int amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println(amount + " 원이 정상적으로 입금되었습니다.");
        } else {
            System.out.println("잘못된 금액입니다.");
        }
    }

    public void withdraw(int amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println(amount + " 원이 정상적으로 출금되었습니다.");
        } else {
            System.out.println("잘못된 금액입니다.");
        }
    }
}
