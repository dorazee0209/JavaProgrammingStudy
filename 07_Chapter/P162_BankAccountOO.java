class BankAccount{
    static int balance = 0;

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

public class P162_BankAccountOO {
    public static void main(String[] args) {
        BankAccount Yoon = new BankAccount();
        BankAccount Park = new BankAccount();

        Yoon.deposit(5_000);
        Park.deposit(3_000);

        Yoon.checkBalance();
        Park.checkBalance();

        Yoon.withdraw(2_000);
        Park.withdraw(2_000);

        Yoon.checkBalance();
        Park.checkBalance();
    }
}