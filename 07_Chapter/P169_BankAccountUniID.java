public class BankAccount{
    String acc_number;
    String ssn;
    int balance;

    public void initAccoumt(String acc, String ss, int bal) {
        acc_number = acc;
        ssn = ss;
        balance = bal;
    }

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
        System.out.println("Account number:" + acc_number);
        System.out.println("SSN: " + ssn);
        System.out.println("Balance: " + balance);
        return balance;
    }
}

public class P169_BankAccountUniID {
    static void main(String[] args) {
        BankAccount yoon = new BankAccount();
        yoon.initAccoumt("12-34-89", "990990-9090990", 10_000);

        BankAccount park = new BankAccount();
        park.initAccoumt("33-55-09", "770088-5959007", 10_000);

        yoon.deposit(5_000);
        park.deposit(3_000);

        yoon.withdraw(2_000);
        park.withdraw(2_000);

        yoon.checkBalance();
        park.checkBalance();
    }
}