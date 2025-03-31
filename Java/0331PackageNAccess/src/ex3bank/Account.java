package ex3bank;

public class Account {
    private int balance;
    public static final int MIN_BALANCE = 0;
    public static final int MAX_BALANCE = 1000000;

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        if (balance < MIN_BALANCE || balance > MAX_BALANCE) {
            System.out.println("입력값이 허용 범위를 벗어났습니다.");
            return;
        }

        this.balance = balance;
    }
}
