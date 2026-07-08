class BankAccount{
    int balance = 0;

    public int deposit(int amount) {
        balance += amount;
        return balance;
    }
    public int withdraw(int amount) {
        if(balance - amount >= 0) {
            balance -= amount;
            return balance;
        }
        else {
            System.out.println("err");
            return balance;
        }
    }
    public int checkBalance() {
        System.out.println("Balance: " + balance);
        return balance;
    }
}

public class P165_PassingRef {
    public static void main(String[] args) {
        BankAccount ref = new BankAccount();
        ref.deposit(3_000);
        ref.withdraw(300);
        check(ref);
    }

    public static void check(BankAccount acc) {
        acc.checkBalance();
    }
}