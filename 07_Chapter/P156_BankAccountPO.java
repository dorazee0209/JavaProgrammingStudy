public class P156_BankAccountPO {
    static int balance = 0;

    public static void main(String[] args) {
        deposit(10_000);
        checkBalance();
        withdraw(3_000);
        checkBalance();
    }

    public static int deposit(int amount) {
        balance += amount;
        return balance;
    }
    public static int withdraw(int amount) {
        if(balance - amount >= 0) {
            balance -= amount;
            return balance;
        }
        else {
            System.out.println("err");
            return balance;
        }
    }
    public static int checkBalance() {
        System.out.println("Balance: " + balance);
        return balance;
    }
}