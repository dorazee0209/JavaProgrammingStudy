

public class BankAccount{
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

public class P164_DupRef {
    static void main(String[] args) {
        BankAccount ref1= new BankAccount();
        BankAccount ref2 = ref1;

        ref1.deposit(3_000);
        ref2.deposit(2_000);

//        ref1.checkBalance();
//        ref2.checkBalance();

        ref1.withdraw(400);
        ref2.withdraw(300);

        ref1.checkBalance();
        ref2.checkBalance();
    }
}