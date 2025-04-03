package java_22_99_ex2_Account;

public class BankAccount {
    private String accountNumber;
    private int balance;

    BankAccount(String accountNumber) {
        this.accountNumber = accountNumber;
        this.balance = 0;
    }

    BankAccount(String accountNumber, int balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public void setBalance(int balance) {
        if (balance < 0) {
            System.out.println("잔액은 음수일 수 없습니다.");
        } else {
            this.balance = balance;
        }
    }
    public int getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(balance);
        sb.append("원 (계좌번호=");
        sb.append(accountNumber);
        sb.append(")");
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj instanceof BankAccount account) {
            String tempStr1 = account.accountNumber.trim();
            String tempStr2 = this.accountNumber.trim();
            return tempStr1.equals(tempStr2);
        }
        return false;
    }
}
