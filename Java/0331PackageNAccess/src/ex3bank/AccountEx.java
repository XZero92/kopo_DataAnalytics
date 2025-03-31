package ex3bank;

public class AccountEx {
    public static void main(String[] args) {
        Account account = new Account();
        account.setBalance(10000);
        System.out.println("현재 잔고는 " + account.getBalance() + " 원입니다.");

        account.setBalance(-100); // 허용 범위를 벗어난 값
        System.out.println("현재 잔고는 " + account.getBalance() + " 원입니다.");

        account.setBalance(200000);
        System.out.println("현재 잔고는 " + account.getBalance() + " 원입니다.");

        account.setBalance(300000);
        System.out.println("현재 잔고는 " + account.getBalance() + " 원입니다.");
    }
}
