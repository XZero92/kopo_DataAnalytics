package java_22_99_ex2_Account;

public class BankAccountEx {
    public static void main(String[] args) {
        BankAccount account1 = new BankAccount("4649");
        BankAccount account2 = new BankAccount("   4649", 10000);
        BankAccount account3 = new BankAccount("   4649    ", 20000);
        BankAccount account4 = new BankAccount("4749", 10000);

        System.out.println(account1);
        System.out.println(account2);
        System.out.println(account3);

        System.out.println("계좌1과 계좌2는 동등합니까? " + account1.equals(account2));
        System.out.println("계좌2와 계좌3은 동등합니까? " + account2.equals(account3));
        System.out.println("계좌1과 계좌4는 동등합니까? " + account1.equals(account4));
    }
}
