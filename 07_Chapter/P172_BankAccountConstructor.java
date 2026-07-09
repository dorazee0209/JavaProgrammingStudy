class BankAccount {
    String accNumber;
    String ssNumber;
    int balance;

    public BankAccount(String acc, String ss, int bal) { // constructor
        accNumber = acc;
        ssNumber = ss;
        balance = bal;
    }

    public int deposit(int amount) {
        balance += amount;
        return balance;
    }

    public int withdraw(int amount) {
        if(balance - amount < 0) {
            System.out.println("err");
            return balance;
        }
        else {
            balance -= amount;
            return balance;
        }
    }

    public void checkBal() {
        System.out.println("Account number: " + accNumber);
        System.out.println("SSN: " + ssNumber);
        System.out.println("Balance: " + balance);
    }
}

public class P172_BankAccountConstructor {
    public static void main(String[] args) {
        BankAccount yoon = new BankAccount("12-34-89", "990990-9090990", 10_000);
        BankAccount park = new BankAccount("33-55-09", "770088-5959007", 10_000);

        yoon.deposit(5_000);
        park.deposit(3_000);

        yoon.withdraw(2_000);
        park.withdraw(2_000);

        yoon.checkBal();
        park.checkBal();
    }
}

